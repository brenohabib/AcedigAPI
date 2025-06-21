package API.LibraryManagement.Acedig.Repository;

import API.LibraryManagement.Acedig.Data.Model.Emprestimo;
import API.LibraryManagement.Acedig.Data.Model.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    boolean existsByUsuarioIdAndLivroFisicoIdAndStatus(Long usuarioId, Long livroId, StatusEmprestimo statusEmprestimo);

    long countByUsuarioIdAndStatus(Long usuarioId, StatusEmprestimo statusEmprestimo);
    List<Emprestimo> findByUsuarioId(Long usuarioId);

    List<Emprestimo> findByStatus(StatusEmprestimo statusEmprestimo);

    List<Emprestimo> findByStatusAndDataPrevistaDevolcaoBefore(StatusEmprestimo status, Date dataPrevistaDevolcao);

    List<Emprestimo> findByLivroFisicoId(Long livroId);

    List<Emprestimo> findByStatusAndDataPrevistaDevolcao(StatusEmprestimo status, Date dataPrevistaDevolcao);
}
