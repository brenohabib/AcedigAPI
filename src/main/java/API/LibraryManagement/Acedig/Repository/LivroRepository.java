package API.LibraryManagement.Acedig.Repository;

import API.LibraryManagement.Acedig.Data.Model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface LivroRepository<T extends Livro> extends JpaRepository<T, Long> {
    Page<T> findByTituloContainingOrAutorContaining(String titulo, String autor, Pageable pageable);
}
