package API.LibraryManagement.Acedig.Service;

import API.LibraryManagement.Acedig.Data.Model.Notificacao;
import API.LibraryManagement.Acedig.Data.Model.TipoNotificacao;
import API.LibraryManagement.Acedig.Data.Model.Usuario;
import API.LibraryManagement.Acedig.Repository.NotificacaoRepository;
import API.LibraryManagement.Acedig.Repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository, UsuarioRepository usuarioRepository) {
        this.notificacaoRepository = notificacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void sendNotificacao(Long usuarioId, String mensagem, TipoNotificacao tipoNotificacao) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Notificacao notificacao = new Notificacao();
        notificacao.setUsuario(usuario);
        notificacao.setMensagem(mensagem);
        notificacao.setTipoNotificacao(tipoNotificacao);
        notificacaoRepository.save(notificacao);

        //TODO: enviar por email
    }

    public List<Notificacao> findNotLida(Long usuarioId) {
        return notificacaoRepository.findByUsuarioIdAndLidaFalse(usuarioId);
    }

    public List<Notificacao> findAll(Long usuarioId) {
        return notificacaoRepository.findByUsuarioId(usuarioId);
    }

    public void markAsLida(Long notificacaoId) {
        Notificacao notificacao = notificacaoRepository.findById(notificacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Notificação não encontrada"));

        notificacao.setLida(true);
        notificacaoRepository.save(notificacao);
    }

}
