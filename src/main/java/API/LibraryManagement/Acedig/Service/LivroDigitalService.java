package API.LibraryManagement.Acedig.Service;

import API.LibraryManagement.Acedig.Repository.LivroDigitalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LivroDigitalService {
    LivroDigitalRepository livroDigitalRepository;

    public LivroDigitalService(LivroDigitalRepository livroDigitalRepository) {
        this.livroDigitalRepository = livroDigitalRepository;
    }

}
