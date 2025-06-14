package API.LibraryManagement.Acedig.Controller;

import API.LibraryManagement.Acedig.Data.Model.Usuario;
import API.LibraryManagement.Acedig.Service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

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
        Optional<Usuario> usuario = usuarioService.findByEmail(request.getEmail());

        if (usuario.isPresent() && usuarioService.validarCredenciais(request.getEmail(), request.getSenha())) {
            Map<String, String> response = new LinkedHashMap<>();
            response.put("nome", usuario.get().getNome());
            response.put("senha", usuario.get().getSenha());

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
