package API.LibraryManagement.Acedig.Service;

import API.LibraryManagement.Acedig.Data.Model.Usuario;
import API.LibraryManagement.Acedig.Repository.UsuarioRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<Usuario> update(Usuario newUsuario, Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(newUsuario.getNome());
                    usuario.setEmail(newUsuario.getEmail());

                    // Só criptografa a senha se ela foi alterada (não está vazia)
                    if (newUsuario.getSenha() != null && !newUsuario.getSenha().isEmpty()) {
                        usuario.setSenha(passwordEncoder.encode(newUsuario.getSenha()));
                    }

                    usuario.setTelefone(newUsuario.getTelefone());
                    return usuarioRepository.save(usuario);
                });
    }

    public Optional<Usuario> login(String email, String senha) {
        return usuarioRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(senha, user.getSenha()));
    }

    public void registrar(Usuario usuario) {
        if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Email existente");
        }

        if(!isValidEmail(usuario.getEmail())) {
            throw new RuntimeException("Email invalido");
        }

        if(usuario.getSenha().length() < 8) {
            throw new RuntimeException("Senha muito curta");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(usuario.getNome());
        novoUsuario.setEmail(usuario.getEmail());
        novoUsuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        novoUsuario.setTelefone(usuario.getTelefone());

        usuarioRepository.save(novoUsuario);
    }

    public static boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    public boolean validarCredenciais(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        return usuario.isPresent() && passwordEncoder.matches(senha, usuario.get().getSenha());
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        return usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}