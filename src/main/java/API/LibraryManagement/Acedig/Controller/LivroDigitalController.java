package API.LibraryManagement.Acedig.Controller;

import API.LibraryManagement.Acedig.Data.Model.LivroDigital;
import API.LibraryManagement.Acedig.Service.LivroDigitalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livro-digital")
public class LivroDigitalController {
    private final LivroDigitalService livroDigitalService;

    public LivroDigitalController(LivroDigitalService livroDigitalService) {
        this.livroDigitalService = livroDigitalService;
    }

    @PostMapping
    LivroDigital create(@RequestBody LivroDigital livroDigital) {
        return livroDigitalService.create(livroDigital);
    }
}
