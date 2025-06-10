package API.LibraryManagement.Acedig.Repository;

import API.LibraryManagement.Acedig.Model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
