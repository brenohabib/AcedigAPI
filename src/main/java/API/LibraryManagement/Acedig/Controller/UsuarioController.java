package API.LibraryManagement.Acedig.Controller;

import API.LibraryManagement.Acedig.Objects.Usuario;
import API.LibraryManagement.Acedig.Repository.UsuarioRepository;
import API.LibraryManagement.Acedig.Service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    UsuarioController(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuarios")
    List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @PostMapping("/usuarios")
    Usuario create(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/usuarios/{id}")
    Optional<Usuario> findById(@PathVariable Long id) {
        return usuarioRepository.findById(id);
    }

    @PutMapping("usuarios/{id}")
    Optional<Usuario> update(@PathVariable Long id, @RequestBody Usuario newUsuario) {
        return usuarioService.update(newUsuario, id);
    }

    @DeleteMapping("/usuarios/{id}")
    void delete(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}
