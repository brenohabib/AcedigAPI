package API.LibraryManagement.Acedig.Controller;

import API.LibraryManagement.Acedig.Data.Mapper;
import API.LibraryManagement.Acedig.Data.Model.Emprestimo;
import API.LibraryManagement.Acedig.Service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    public ResponseEntity<?> realizarEmprestimo(
            @RequestParam Long usuarioId,
            @RequestParam Long livroId,
            @RequestParam(required = false) Integer diasEmprestimo) {
        try {
            Emprestimo emprestimo = emprestimoService.realizarEmprestimo(usuarioId, livroId, diasEmprestimo);
            return ResponseEntity.status(HttpStatus.CREATED).body(emprestimo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/devolver")
    public ResponseEntity<?> devolverLivro(@PathVariable Long id) {
        try {
            Emprestimo emprestimo = emprestimoService.devolverLivro(id);
            return ResponseEntity.ok(emprestimo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/renovar")
    public ResponseEntity<?> renovarEmprestimo(
            @PathVariable Long id,
            @RequestParam(required = false) Integer diasAdicionais) {
        try {
            Emprestimo emprestimo = emprestimoService.renovarEmprestimo(id, diasAdicionais);
            return ResponseEntity.ok(emprestimo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> getEmprestimosByUsuario(@PathVariable Long usuarioId) {
        List<Emprestimo> emprestimos = emprestimoService.findEmprestimosByUsuario(usuarioId);
        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping("/ativos")
    public ResponseEntity<?> getEmprestimosAtivos() {
        List<Emprestimo> emprestimos = emprestimoService.findEmprestimosAtivos();
        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping("/atraso")
    public ResponseEntity<?> getEmprestimosEmAtraso() {
        List<Emprestimo> emprestimos = emprestimoService.findEmprestimosEmAtraso();
        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping("/livro/{livroId}")
    public ResponseEntity<?> getEmprestimosByLivro(@PathVariable Long livroId) {
        List<Emprestimo> emprestimos = emprestimoService.findEmprestimosByLivro(livroId);
        return ResponseEntity.ok(emprestimos);
    }

    @PostMapping("/notificar-vencimentos")
    public ResponseEntity<String> notificarVencimentosHoje() {
        emprestimoService.notificarEmprestimosVenceremHoje();
        return ResponseEntity.ok("Notificações de vencimento enviadas");
    }

    @PostMapping("/notificar-atrasos")
    public ResponseEntity<String> notificarAtrasos() {
        emprestimoService.notificarEmprestimosEmAtraso();
        return ResponseEntity.ok("Notificações de atraso enviadas");
    }
}