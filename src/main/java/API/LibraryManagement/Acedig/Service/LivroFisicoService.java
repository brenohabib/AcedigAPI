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
    protected void dataToUpdate(LivroFisico livroExistente, LivroFisico livroAtualizado) {
        livroExistente.setTitulo(livroAtualizado.getTitulo());
        livroExistente.setAutor(livroAtualizado.getAutor());
        livroExistente.setEditora(livroAtualizado.getEditora());
        livroExistente.setIsbn(livroAtualizado.getIsbn());
        livroExistente.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
        livroExistente.setQuantidadeTotal(livroAtualizado.getQuantidadeTotal());
    }
}