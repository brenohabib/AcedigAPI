package API.LibraryManagement.Acedig.Controller;

import API.LibraryManagement.Acedig.Data.DTO.UsuarioDTO;
import API.LibraryManagement.Acedig.Data.Mapper;
import API.LibraryManagement.Acedig.Data.Model.Usuario;
import API.LibraryManagement.Acedig.Service.UsuarioService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public UsuarioDTO findById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(Mapper::toUsuarioDTO).orElse(null);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Usuario newUsuario) {
        if (newUsuario == null) {
            return ResponseEntity.badRequest().build();
        }
        usuarioService.update(newUsuario, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
