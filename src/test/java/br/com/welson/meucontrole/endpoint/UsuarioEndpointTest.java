package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.dto.UsuarioDTO;
import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import br.com.welson.meucontrole.persistencia.repositorios.AtivacaoContaRepositorio;
import br.com.welson.meucontrole.persistencia.repositorios.ContaRepositorio;
import br.com.welson.meucontrole.persistencia.repositorios.UsuarioRepositorio;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class UsuarioEndpointTest {

    @MockBean
    private UsuarioRepositorio usuarioRepositorio;

    @MockBean
    private AtivacaoContaRepositorio ativacaoContaRepositorio;

    @MockBean
    private ContaRepositorio contaRepositorio;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private Usuario usuario = mockUsuario();
    private UsuarioDTO usuarioDTO = mockUsuarioDTO();

    public static UsuarioDTO mockUsuarioDTO() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("welsonlimawlsn@gmail.com");
        usuarioDTO.setNome("Welson");
        usuarioDTO.setSenha("123456789");
        usuarioDTO.setSobrenome("Teles");
        usuarioDTO.setUsuario("welsonlimawlsn");
        return usuarioDTO;
    }

    public static Usuario mockUsuario() {
        Usuario usuario = mockUsuarioDTO().convertToObject();
        usuario.setId(1L);
        return usuario;
    }

    @Before
    public void setupMock() {
        BDDMockito.when(usuarioRepositorio.save(usuarioDTO.convertToObject())).thenReturn(usuario);
    }

    @Test
    public void criarUsuarioDeveRetornarCreatedEObjetoUsuario() {
        ResponseEntity<Usuario> exchange = testRestTemplate.exchange("/novo-usuario", HttpMethod.POST, new HttpEntity<>(usuarioDTO), Usuario.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(exchange.getBody()).isEqualTo(usuario);
    }

    @Test
    public void criarUsuarioNomeNullDeveRetornarBadRequest() {
        UsuarioDTO usuarioFaltandoNome = mockUsuarioDTO();
        usuarioFaltandoNome.setNome(null);
        tentandoCriarUsuarioComDadosIncompletos(usuarioFaltandoNome);
    }

    @Test
    public void criarUsuarioNomeEmptyDeveRetornarBadRequest() {
        UsuarioDTO usuarioFaltandoNome = mockUsuarioDTO();
        usuarioFaltandoNome.setNome("");
        tentandoCriarUsuarioComDadosIncompletos(usuarioFaltandoNome);
    }

    @Test
    public void criarUsuarioNomeDeUsuarioNullDeveRetornarBadRequest() {
        UsuarioDTO usuarioFaltandoNome = mockUsuarioDTO();
        usuarioFaltandoNome.setUsuario(null);
        tentandoCriarUsuarioComDadosIncompletos(usuarioFaltandoNome);
    }

    @Test
    public void criarUsuarioNomeDeUsuarioEmptyDeveRetornarBadRequest() {
        UsuarioDTO usuarioFaltandoNome = mockUsuarioDTO();
        usuarioFaltandoNome.setUsuario("");
        tentandoCriarUsuarioComDadosIncompletos(usuarioFaltandoNome);
    }

    @Test
    public void criarUsuarioSobrenomeNullDeveRetornarBadRequest() {
        UsuarioDTO usuarioFaltandoNome = mockUsuarioDTO();
        usuarioFaltandoNome.setSobrenome(null);
        tentandoCriarUsuarioComDadosIncompletos(usuarioFaltandoNome);
    }

    @Test
    public void criarUsuarioSobrenomeEmptyDeveRetornarBadRequest() {
        UsuarioDTO usuarioFaltandoNome = mockUsuarioDTO();
        usuarioFaltandoNome.setSobrenome("");
        tentandoCriarUsuarioComDadosIncompletos(usuarioFaltandoNome);
    }

    @Test
    public void criarUsuarioSenhaNullDeveRetornarBadRequest() {
        UsuarioDTO usuarioFaltandoNome = mockUsuarioDTO();
        usuarioFaltandoNome.setSenha(null);
        tentandoCriarUsuarioComDadosIncompletos(usuarioFaltandoNome);
    }

    @Test
    public void criarUsuarioSenhaEmptyDeveRetornarBadRequest() {
        UsuarioDTO usuarioFaltandoNome = mockUsuarioDTO();
        usuarioFaltandoNome.setSenha("");
        tentandoCriarUsuarioComDadosIncompletos(usuarioFaltandoNome);
    }

    @Test
    public void criarUsuarioEmailNullDeveRetornarBadRequest() {
        UsuarioDTO usuarioFaltandoNome = mockUsuarioDTO();
        usuarioFaltandoNome.setEmail(null);
        tentandoCriarUsuarioComDadosIncompletos(usuarioFaltandoNome);
    }

    @Test
    public void criarUsuarioEmailEmptyDeveRetornarBadRequest() {
        UsuarioDTO usuarioFaltandoNome = mockUsuarioDTO();
        usuarioFaltandoNome.setEmail("");
        tentandoCriarUsuarioComDadosIncompletos(usuarioFaltandoNome);
    }

    @Test
    public void criarUsuarioEmailJaExistebteDeveRetornarBadRequest() {
        BDDMockito.when(usuarioRepositorio.findByEmail(usuarioDTO.getEmail())).thenReturn(Optional.of(usuario));
        tentandoCriarUsuarioComDadosIncompletos(usuarioDTO);
    }

    @Test
    public void criarUsuarioUsuarioJaExistenteDeveRetornarBadRequest() {
        BDDMockito.when(usuarioRepositorio.findByUsuario(usuarioDTO.getUsuario())).thenReturn(Optional.of(usuario));
        tentandoCriarUsuarioComDadosIncompletos(usuarioDTO);
    }

    private void tentandoCriarUsuarioComDadosIncompletos(UsuarioDTO usuarioFaltandoNome) {
        ResponseEntity<Usuario> exchange = testRestTemplate.exchange("/novo-usuario", HttpMethod.POST, new HttpEntity<>(usuarioFaltandoNome), Usuario.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}
