package API.LibraryManagement.Acedig.Service;

import API.LibraryManagement.Acedig.Data.Model.LivroDigital;
import API.LibraryManagement.Acedig.Repository.LivroDigitalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LivroDigitalService extends CommonLivroService<LivroDigital> {

    public LivroDigitalService(LivroDigitalRepository livroDigitalRepository) {
        super(livroDigitalRepository);
    }

    @Override
    protected void extraDataUpdate(LivroDigital livroExistente, LivroDigital livroAtualizado) {
        if  (livroAtualizado.getTipoLivro() != null) {
            livroExistente.setTipoLivro(livroAtualizado.getTipoLivro());
        }
    }
}
