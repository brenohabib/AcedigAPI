package API.LibraryManagement.Acedig.Controller;

import API.LibraryManagement.Acedig.Data.Model.LivroDigital;
import API.LibraryManagement.Acedig.Service.LivroDigitalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro-digital")
public class LivroDigitalController {
    private final LivroDigitalService livroDigitalService;

    public LivroDigitalController(LivroDigitalService livroDigitalService) {
        this.livroDigitalService = livroDigitalService;
    }

    @GetMapping
    public List<LivroDigital> findAll() {
        return livroDigitalService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<LivroDigital> findById(@PathVariable Long id) {
        return livroDigitalService.findById(id);
    }

    @GetMapping("/search")
    public Page<LivroDigital> findBySearch(@RequestParam(required = false) String termo, Pageable pageable) {
        return livroDigitalService.find(termo, pageable);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody LivroDigital livroDigital) {
        livroDigitalService.create(livroDigital);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody LivroDigital livroDigital) {
        if (livroDigital.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        livroDigitalService.update(livroDigital);
        return ResponseEntity.ok().build();
    }
}
