package API.LibraryManagement.Acedig.Repository;

import API.LibraryManagement.Acedig.Data.Model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    List<Notificacao> findByUsuarioId(Long usuarioId);
    List<Notificacao> findByUsuarioIdAndLidaFalse(Long usuarioId);

}
