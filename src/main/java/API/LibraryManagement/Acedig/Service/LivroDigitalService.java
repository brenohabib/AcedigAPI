package API.LibraryManagement.Acedig.Service;

import API.LibraryManagement.Acedig.Data.Model.LivroDigital;
import API.LibraryManagement.Acedig.Data.Model.LivroFisico;
import API.LibraryManagement.Acedig.Repository.LivroDigitalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LivroDigitalService extends CommonLivroService<LivroDigital> {

    public LivroDigitalService(LivroDigitalRepository livroDigitalRepository) {
        super(livroDigitalRepository);
    }

    @Override
    protected void dataToUpdate(LivroDigital livroExistente, LivroDigital livroAtualizado) {
        livroExistente.setTitulo(livroAtualizado.getTitulo());
        livroExistente.setAutor(livroAtualizado.getAutor());
        livroExistente.setEditora(livroAtualizado.getEditora());
        livroExistente.setIsbn(livroAtualizado.getIsbn());
        livroExistente.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
        livroExistente.setTipoLivro(livroAtualizado.getTipoLivro());
    }
}
