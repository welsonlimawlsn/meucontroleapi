package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.dto.UsuarioDTO;
import br.com.welson.meucontrole.persistencia.modelos.AtivacaoConta;
import br.com.welson.meucontrole.persistencia.modelos.Conta;
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

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class UsuarioEndpointTest {

    @MockBean
    private UsuarioRepositorio usuarioRepositorio;

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
    public void criarUsuarioDeveRetornar201() {
        ResponseEntity<String> exchange = testRestTemplate.exchange("/novo-usuario", HttpMethod.POST, new HttpEntity<>(usuarioDTO), String.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

}
