package API.LibraryManagement.Acedig.Controller;

import API.LibraryManagement.Acedig.Data.Model.Usuario;
import API.LibraryManagement.Acedig.Service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @PostMapping
    Usuario create(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @GetMapping("/{id}")
    Optional<Usuario> findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PutMapping("/{id}")
    Optional<Usuario> update(@PathVariable Long id, @RequestBody Usuario newUsuario) {
        return usuarioService.update(newUsuario, id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}
