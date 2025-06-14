package API.LibraryManagement.Acedig.Service;

import API.LibraryManagement.Acedig.Data.Model.Usuario;
import API.LibraryManagement.Acedig.Repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuarioTeste;

    @BeforeEach
    void setUp() {
        usuarioRepository.deleteAll();
        usuarioTeste = new Usuario();
        usuarioTeste.setNome("João Silva");
        usuarioTeste.setEmail("joao@teste.com");
        usuarioTeste.setSenha("12345678");
        usuarioTeste.setTelefone("11999999999");
    }

    @Test
    void testRegistrar_NovoUsuario_DeveSalvarComSucesso() {
        usuarioService.registrar(usuarioTeste);
        Optional<Usuario> usuarioSalvo = usuarioRepository.findByEmail("joao@teste.com");
        assertTrue(usuarioSalvo.isPresent());
        assertEquals("João Silva", usuarioSalvo.get().getNome());
        assertEquals("joao@teste.com", usuarioSalvo.get().getEmail());
    }

    @Test
    void testRegistrar_EmailExistente_DeveLancarExcecao() {
        usuarioRepository.save(usuarioTeste);

        Usuario usuarioDuplicado = new Usuario();
        usuarioDuplicado.setNome("Maria Santos");
        usuarioDuplicado.setEmail("joao@teste.com");
        usuarioDuplicado.setSenha("87654321");
        usuarioDuplicado.setTelefone("11888888888");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> usuarioService.registrar(usuarioDuplicado));
        assertEquals("Email existente", exception.getMessage());
    }

    @Test
    void testLogin_CredenciaisValidas_DeveRetornarUsuario() {
        usuarioRepository.save(usuarioTeste);
        Optional<Usuario> resultado = usuarioService.login("joao@teste.com", "12345678");
        assertTrue(resultado.isPresent());
        assertEquals("João Silva", resultado.get().getNome());
    }

    @Test
    void testLogin_SenhaInvalida_DeveRetornarVazio() {
        usuarioRepository.save(usuarioTeste);
        Optional<Usuario> resultado = usuarioService.login("joao@teste.com", "senhaerrada");
        assertFalse(resultado.isPresent());
    }

    @Test
    void testLogin_EmailInexistente_DeveRetornarVazio() {
        Optional<Usuario> resultado = usuarioService.login("email@inexistente.com", "123456");
        assertFalse(resultado.isPresent());
    }

    @Test
    void testUpdate_UsuarioExistente_DeveAtualizarComSucesso() {
        Usuario usuarioSalvo = usuarioRepository.save(usuarioTeste);
        Long id = usuarioSalvo.getId();

        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNome("João Santos");
        usuarioAtualizado.setEmail("joao.santos@teste.com");
        usuarioAtualizado.setSenha("novaSenha");
        usuarioAtualizado.setTelefone("11777777777");

        Optional<Usuario> resultado = usuarioService.update(usuarioAtualizado, id);
        assertTrue(resultado.isPresent());
        assertEquals("João Santos", resultado.get().getNome());
        assertEquals("joao.santos@teste.com", resultado.get().getEmail());
        assertEquals("novaSenha", resultado.get().getSenha());
        assertEquals("11777777777", resultado.get().getTelefone());
    }

    @Test
    void testUpdate_UsuarioInexistente_DeveRetornarVazio() {
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNome("Nome Teste");
        Optional<Usuario> resultado = usuarioService.update(usuarioAtualizado, 999L);
        assertFalse(resultado.isPresent());
    }

    @Test
    void testFindById_UsuarioExistente_DeveRetornarUsuario() {
        Usuario usuarioSalvo = usuarioRepository.save(usuarioTeste);
        Long id = usuarioSalvo.getId();
        Optional<Usuario> resultado = usuarioService.findById(id);

        assertTrue(resultado.isPresent());
        assertEquals("João Silva", resultado.get().getNome());
    }

    @Test
    void testFindById_UsuarioInexistente_DeveRetornarVazio() {
        Optional<Usuario> resultado = usuarioService.findById(999L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void testFindAll_ComUsuarios_DeveRetornarLista() {
        usuarioRepository.save(usuarioTeste);

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Maria Santos");
        usuario2.setEmail("maria@teste.com");
        usuario2.setSenha("87654321");
        usuario2.setTelefone("11888888888");
        usuarioRepository.save(usuario2);
        List<Usuario> resultado = usuarioService.findAll();

        assertEquals(2, resultado.size());
    }

    @Test
    void testFindAll_SemUsuarios_DeveRetornarListaVazia() {
        List<Usuario> resultado = usuarioService.findAll();
        assertTrue(resultado.isEmpty());
    }

    @Test
    void testSave_NovoUsuario_DeveSalvarComSucesso() {
        Usuario usuarioSalvo = usuarioService.save(usuarioTeste);

        assertNotNull(usuarioSalvo.getId());
        assertEquals("João Silva", usuarioSalvo.getNome());

        Optional<Usuario> usuarioNoBanco = usuarioRepository.findById(usuarioSalvo.getId());
        assertTrue(usuarioNoBanco.isPresent());
    }

    @Test
    void testDelete_UsuarioExistente_DeveRemoverDobanco() {
        Usuario usuarioSalvo = usuarioRepository.save(usuarioTeste);
        Long id = usuarioSalvo.getId();

        assertTrue(usuarioRepository.findById(id).isPresent());

        usuarioService.delete(id);

        assertFalse(usuarioRepository.findById(id).isPresent());
    }

    @Test
    void testDelete_UsuarioInexistente_NaoDeveLancarExcecao() {
        assertDoesNotThrow(() -> usuarioService.delete(999L));
    }
}