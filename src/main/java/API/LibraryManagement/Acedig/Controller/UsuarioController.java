package API.LibraryManagement.Acedig.Controller;

import API.LibraryManagement.Acedig.Model.Usuario;
import API.LibraryManagement.Acedig.Service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuarios")
    List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @PostMapping("/usuarios")
    Usuario create(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @GetMapping("/usuarios/{id}")
    Optional<Usuario> findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PutMapping("usuarios/{id}")
    Optional<Usuario> update(@PathVariable Long id, @RequestBody Usuario newUsuario) {
        return usuarioService.update(newUsuario, id);
    }

    @DeleteMapping("/usuarios/{id}")
    void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}
