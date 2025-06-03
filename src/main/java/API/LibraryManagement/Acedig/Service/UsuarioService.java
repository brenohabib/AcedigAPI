package API.LibraryManagement.Acedig.Service;

import API.LibraryManagement.Acedig.Objects.Usuario;
import API.LibraryManagement.Acedig.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> update(Usuario newUsuario, Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(newUsuario.getNome());
                    usuario.setEmail(newUsuario.getEmail());
                    usuario.setSenha(newUsuario.getSenha());
                    usuario.setTelefone(newUsuario.getTelefone());
                    return usuarioRepository.save(usuario);
                });
    }


}
