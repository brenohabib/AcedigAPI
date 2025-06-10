package API.LibraryManagement.Acedig.Controller;

import API.LibraryManagement.Acedig.Model.Usuario;
import API.LibraryManagement.Acedig.Service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody Usuario request) {
        try {
            usuarioService.registrar(request);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario request) {
        if (usuarioService.validarCredenciais(request.getEmail(), request.getSenha())) {
            return ResponseEntity.status(HttpStatus.OK).body("Usuario encontrado");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
