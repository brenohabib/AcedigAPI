package API.LibraryManagement.Acedig.Repository;

import API.LibraryManagement.Acedig.Model.LivroDigital;
import API.LibraryManagement.Acedig.Model.LivroFisico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroDigitalRepository extends JpaRepository<LivroDigital, Long> {

}
