package API.LibraryManagement.Acedig.Repository;

import API.LibraryManagement.Acedig.Objects.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
