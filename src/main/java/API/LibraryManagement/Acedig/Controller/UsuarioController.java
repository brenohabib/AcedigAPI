package API.LibraryManagement.Acedig.Controller;

import API.LibraryManagement.Acedig.Data.DTO.UsuarioDTO;
import API.LibraryManagement.Acedig.Data.Mapper;
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
    List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        return Mapper.toUsuarioDTOList(usuarios);
    }

    @PostMapping
    Usuario create(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @GetMapping("/{id}")
    UsuarioDTO findById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(Mapper::toUsuarioDTO).orElse(null);
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
