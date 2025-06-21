package API.LibraryManagement.Acedig.Service;

import API.LibraryManagement.Acedig.Data.Model.*;
import API.LibraryManagement.Acedig.Repository.EmprestimoRepository;
import API.LibraryManagement.Acedig.Repository.LivroFisicoRepository;
import API.LibraryManagement.Acedig.Repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroFisicoRepository livroFisicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final NotificacaoService notificacaoService;

    public EmprestimoService(EmprestimoRepository emprestimoRepository,
                             LivroFisicoRepository livroFisicoRepository,
                             UsuarioRepository usuarioRepository,
                             NotificacaoService notificacaoService) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroFisicoRepository = livroFisicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.notificacaoService = notificacaoService;
    }

    public Emprestimo realizarEmprestimo(Long usuarioId, Long livroId, Integer diasEmprestimo) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        LivroFisico livro = livroFisicoRepository.findById(livroId)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));

        if (livro.getQuantidadeDisponivel() <= 0) {
            throw new RuntimeException("Não há exemplares disponíveis para empréstimo");
        }

        if (emprestimoRepository.existsByUsuarioIdAndLivroFisicoIdAndStatus(
                usuarioId, livroId, StatusEmprestimo.ATIVO)) {
            throw new RuntimeException("Usuário já possui empréstimo ativo deste livro");
        }

        long emprestimosAtivos = emprestimoRepository.countByUsuarioIdAndStatus(
                usuarioId, StatusEmprestimo.ATIVO);
        if (emprestimosAtivos >= 3) {
            throw new RuntimeException("Usuário atingiu o limite máximo de empréstimos simultâneos");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivroFisico(livro);
        emprestimo.setDataEmprestimo(Date.valueOf(LocalDate.now()));
        emprestimo.setDataPrevistaDevolcao(
                Date.valueOf(LocalDate.now().plusDays(diasEmprestimo != null ? diasEmprestimo : 14)));
        emprestimo.setStatus(StatusEmprestimo.ATIVO);

        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
        livroFisicoRepository.save(livro);

        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);

        notificacaoService.sendNotificacao(
                usuarioId,
                "Empréstimo realizado com sucesso! Livro: " + livro.getTitulo() +
                        ". Data de devolução: " + emprestimo.getDataDevolcao(),
                TipoNotificacao.LEMBRETE
        );

        return emprestimoSalvo;
    }

    public Emprestimo devolverLivro(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado"));

        if (emprestimo.getStatus() != StatusEmprestimo.ATIVO) {
            throw new RuntimeException("Este empréstimo não está ativo");
        }

        emprestimo.setDataDevolcao(Date.valueOf(LocalDate.now()));
        emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);

        if (LocalDate.now().isAfter(emprestimo.getDataPrevistaDevolcao().toLocalDate())) {
            emprestimo.setStatus(StatusEmprestimo.ATRASADO);
        }

        LivroFisico livro = emprestimo.getLivroFisico();
        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);
        livroFisicoRepository.save(livro);

        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);

        String mensagem = emprestimo.getStatus() == StatusEmprestimo.ATRASADO ?
                "Livro devolvido com atraso: " + livro.getTitulo() :
                "Livro devolvido com sucesso: " + livro.getTitulo();

        notificacaoService.sendNotificacao(
                emprestimo.getUsuario().getId(),
                mensagem,
                TipoNotificacao.LEMBRETE
        );

        return emprestimoSalvo;
    }

    public Emprestimo renovarEmprestimo(Long emprestimoId, Integer diasAdicionais) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado"));

        if (emprestimo.getStatus() != StatusEmprestimo.ATIVO) {
            throw new RuntimeException("Este empréstimo não está ativo");
        }

        if (LocalDate.now().isAfter(emprestimo.getDataPrevistaDevolcao().toLocalDate())) {
            throw new RuntimeException("Não é possível renovar empréstimo em atraso");
        }

        emprestimo.setDataPrevistaDevolcao(
                Date.valueOf(emprestimo.getDataPrevistaDevolcao().toLocalDate().plusDays(diasAdicionais != null ? diasAdicionais : 14))
        );

        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);

        notificacaoService.sendNotificacao(
                emprestimo.getUsuario().getId(),
                "Empréstimo renovado! Nova data de devolução: " + emprestimo.getDataDevolcao(),
                TipoNotificacao.LEMBRETE
        );

        return emprestimoSalvo;
    }

    public List<Emprestimo> findEmprestimosByUsuario(Long usuarioId) {
        return emprestimoRepository.findByUsuarioId(usuarioId);
    }

    public List<Emprestimo> findEmprestimosAtivos() {
        return emprestimoRepository.findByStatus(StatusEmprestimo.ATIVO);
    }

    public List<Emprestimo> findEmprestimosEmAtraso() {
        return emprestimoRepository.findByStatusAndDataPrevistaDevolcaoBefore(
                StatusEmprestimo.ATIVO, Date.valueOf(LocalDate.now()));
    }

    public List<Emprestimo> findEmprestimosByLivro(Long livroId) {
        return emprestimoRepository.findByLivroFisicoId(livroId);
    }

    public void notificarEmprestimosVenceremHoje() {
        List<Emprestimo> emprestimosVencendoHoje = emprestimoRepository
                .findByStatusAndDataPrevistaDevolcao(StatusEmprestimo.ATIVO, Date.valueOf(LocalDate.now()));

        for (Emprestimo emprestimo : emprestimosVencendoHoje) {
            notificacaoService.sendNotificacao(
                    emprestimo.getUsuario().getId(),
                    "Lembrete: O livro '" + emprestimo.getLivroFisico().getTitulo() + "' deve ser devolvido hoje!",
                    TipoNotificacao.LEMBRETE
            );
        }
    }

    public void notificarEmprestimosEmAtraso() {
        List<Emprestimo> emprestimosEmAtraso = findEmprestimosEmAtraso();

        for (Emprestimo emprestimo : emprestimosEmAtraso) {
            long diasAtraso = LocalDate.now().toEpochDay() - emprestimo.getDataPrevistaDevolcao().toLocalDate().toEpochDay();

            notificacaoService.sendNotificacao(
                    emprestimo.getUsuario().getId(),
                    "ATENÇÃO: O livro '" + emprestimo.getLivroFisico().getTitulo() +
                            "' está em atraso há " + diasAtraso + " dias!",
                    TipoNotificacao.COBRANCA
            );
        }
    }
}