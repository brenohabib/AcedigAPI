package API.LibraryManagement.Acedig.Service;

import API.LibraryManagement.Acedig.Data.Model.LivroFisico;
import API.LibraryManagement.Acedig.Repository.LivroFisicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LivroFisicoService extends CommonLivroService<LivroFisico> {

    public LivroFisicoService(LivroFisicoRepository livroFisicoRepository) {
        super(livroFisicoRepository);
    }

    @Override
    protected void extraDataUpdate(LivroFisico livroExistente, LivroFisico livroAtualizado) {
        if  (livroAtualizado.getQuantidadeTotal() > 0) {
            livroExistente.setQuantidadeTotal(livroAtualizado.getQuantidadeTotal());
        }
    }
}
