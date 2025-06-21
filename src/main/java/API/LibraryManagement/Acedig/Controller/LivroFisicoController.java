package API.LibraryManagement.Acedig.Controller;

import API.LibraryManagement.Acedig.Data.Model.LivroFisico;
import API.LibraryManagement.Acedig.Service.LivroFisicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro-fisico")
public class LivroFisicoController {
    private final LivroFisicoService livroFisicoService;

    public LivroFisicoController(LivroFisicoService livroFisicoService) {
        this.livroFisicoService = livroFisicoService;
    }

    @GetMapping
    public List<LivroFisico> findAll() {
        return livroFisicoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<LivroFisico> findById(@PathVariable Long id) {
        return livroFisicoService.findById(id);
    }

    @GetMapping("/search")
    public Page<LivroFisico> findBySearch(@RequestParam(required = false) String termo, Pageable pageable) {
        return livroFisicoService.find(termo, pageable);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody LivroFisico livroFisico) {
        livroFisicoService.create(livroFisico);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody LivroFisico livroFisico) {
        if (livroFisico.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        livroFisicoService.update(livroFisico);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        livroFisicoService.remove(id);
        return  ResponseEntity.ok().build();
    }
}
