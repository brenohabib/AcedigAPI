package API.LibraryManagement.Acedig.Service;

import API.LibraryManagement.Acedig.Model.Notificacao;
import API.LibraryManagement.Acedig.Repository.NotificacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    public List<Notificacao> findNotLida(Long usuarioId) {
        return notificacaoRepository.findByUsuarioIdAndLidaFalse(usuarioId);
    }

    public List<Notificacao> find(Long usuarioId) {
        return notificacaoRepository.findByUsuarioId(usuarioId);
    }

    public void markAsLida(Long notificacaoId) {
        Notificacao notificacao = notificacaoRepository.findById(notificacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Notificação não encontrada"));

        notificacao.setLida(true);
        notificacaoRepository.save(notificacao);
    }
}
