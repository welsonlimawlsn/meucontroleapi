package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.dto.UsuarioDTO;
import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import br.com.welson.meucontrole.persistencia.repositorios.AtivacaoContaRepositorio;
import br.com.welson.meucontrole.persistencia.repositorios.ContaRepositorio;
import br.com.welson.meucontrole.persistencia.repositorios.UsuarioRepositorio;
import br.com.welson.meucontrole.util.ErrorMessages;
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

import java.util.LinkedHashMap;
import java.util.Optional;

import static br.com.welson.meucontrole.util.ErrorMessages.*;
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
    private UsuarioDTO usuarioDTO;

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
        return mockUsuarioDTO().convertToObject();
    }

    @Before
    public void setupMock() {
        usuarioDTO = mockUsuarioDTO();
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
        usuarioDTO.setNome(null);
        ResponseEntity<Object> responseEntity = tentandoCriarUsuarioComDadosIncompletos(usuarioDTO);
        conferirMessagemErro(NOME_USUARIO_OBRIGATORIO, responseEntity);
    }

    @Test
    public void criarUsuarioNomeEmptyDeveRetornarBadRequest() {
        usuarioDTO.setNome("");
        conferirMessagemErro(NOME_USUARIO_OBRIGATORIO, tentandoCriarUsuarioComDadosIncompletos(usuarioDTO));
    }

    @Test
    public void criarUsuarioNomeDeUsuarioNullDeveRetornarBadRequest() {
        usuarioDTO.setUsuario(null);
        conferirMessagemErro(ID_ENTIDADE_NAO_NULO, tentandoCriarUsuarioComDadosIncompletos(usuarioDTO));
    }

    @Test
    public void criarUsuarioNomeDeUsuarioEmptyDeveRetornarBadRequest() {
        usuarioDTO.setUsuario("");
        conferirMessagemErro(USUARIO_USUARIO_OBRIGATORIO, tentandoCriarUsuarioComDadosIncompletos(usuarioDTO));
    }

    @Test
    public void criarUsuarioSobrenomeNullDeveRetornarBadRequest() {
        usuarioDTO.setSobrenome(null);
        conferirMessagemErro(SOBRENOME_USUARIO_OBRIGATORIO, tentandoCriarUsuarioComDadosIncompletos(usuarioDTO));
    }

    @Test
    public void criarUsuarioSobrenomeEmptyDeveRetornarBadRequest() {
        usuarioDTO.setSobrenome("");
        conferirMessagemErro(SOBRENOME_USUARIO_OBRIGATORIO, tentandoCriarUsuarioComDadosIncompletos(usuarioDTO));
    }

    @Test
    public void criarUsuarioSenhaNullDeveRetornarBadRequest() {
        usuarioDTO.setSenha(null);
        conferirMessagemErro(SENHA_USUARIO_OBRIGATORIO, tentandoCriarUsuarioComDadosIncompletos(usuarioDTO));
    }

    @Test
    public void criarUsuarioSenhaEmptyDeveRetornarBadRequest() {
        usuarioDTO.setSenha("");
        conferirMessagemErro(SENHA_USUARIO_OBRIGATORIO, tentandoCriarUsuarioComDadosIncompletos(usuarioDTO));
    }

    @Test
    public void criarUsuarioEmailNullDeveRetornarBadRequest() {
        usuarioDTO.setEmail(null);
        conferirMessagemErro(EMAIL_USUARIO_OBRIGATORIO, tentandoCriarUsuarioComDadosIncompletos(usuarioDTO));
    }

    @Test
    public void criarUsuarioEmailEmptyDeveRetornarBadRequest() {
        usuarioDTO.setEmail("");
        conferirMessagemErro(EMAIL_USUARIO_OBRIGATORIO, tentandoCriarUsuarioComDadosIncompletos(usuarioDTO));
    }

    @Test
    public void criarUsuarioEmailJaExistebteDeveRetornarBadRequest() {
        BDDMockito.when(usuarioRepositorio.findByEmail(usuarioDTO.getEmail())).thenReturn(Optional.of(usuario));
        conferirMessagemErro(EMAIL_USUARIO_DUPLICADO, tentandoCriarUsuarioComDadosIncompletos(usuarioDTO));
    }

    @Test
    public void criarUsuarioUsuarioJaExistenteDeveRetornarBadRequest() {
        BDDMockito.when(usuarioRepositorio.findByUsuario(usuarioDTO.getUsuario())).thenReturn(Optional.of(usuario));
        conferirMessagemErro(USUARIO_USUARIO_DUPLICADO, tentandoCriarUsuarioComDadosIncompletos(usuarioDTO));
    }

    private ResponseEntity<Object> tentandoCriarUsuarioComDadosIncompletos(UsuarioDTO usuarioDTO) {
        ResponseEntity<Object> exchange = testRestTemplate.exchange("/novo-usuario", HttpMethod.POST, new HttpEntity<>(usuarioDTO), Object.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        return exchange;
    }

    private String getMessageError(ResponseEntity<Object> responseEntity) {
        return ((LinkedHashMap<String, String>) responseEntity.getBody()).get("message");
    }

    private void conferirMessagemErro(ErrorMessages errorMessages, ResponseEntity<Object> responseEntity) {
        Assertions.assertThat(getMessageError(responseEntity)).isEqualTo(errorMessages.getMessage());
    }

}
