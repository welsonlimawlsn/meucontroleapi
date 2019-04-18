package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.persistencia.modelos.RecuperacaoSenha;
import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import br.com.welson.meucontrole.persistencia.repositorios.RecuperacaoSenhaRepositorio;
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

import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class RecuperacaoSenhaEndpointTest {

    @MockBean
    private UsuarioRepositorio usuarioRepositorio;

    @MockBean
    private RecuperacaoSenhaRepositorio recuperacaoSenhaRepositorio;

    private Usuario usuario = UsuarioEndpointTest.mockUsuario();
    private RecuperacaoSenha recuperacaoSenha = mockRecuperacaoSenha();

    @Autowired
    private TestRestTemplate testRestTemplate;

    public static RecuperacaoSenha mockRecuperacaoSenha() {
        RecuperacaoSenha recuperacaoSenha = new RecuperacaoSenha("", LocalDateTime.now().plusMinutes(30), false, UsuarioEndpointTest.mockUsuario());
        recuperacaoSenha.setId(1L);
        return recuperacaoSenha;
    }

    @Before
    public void setup() {
        BDDMockito.when(usuarioRepositorio.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));
        BDDMockito.when(recuperacaoSenhaRepositorio.save(recuperacaoSenha)).thenReturn(recuperacaoSenha);
    }

    @Test
    public void pedirRecuperacaoSenhaEmailValidoDeveRetornarOk() {
        ResponseEntity<String> exchange = testRestTemplate.exchange("/recuperacao-senha/pedir/" + usuario.getEmail(), HttpMethod.GET, new HttpEntity<>(null), String.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void pedirRecuperacaoSenhaEmailInvalidoDeveRetornarOk() {
        ResponseEntity<String> exchange = testRestTemplate.exchange("/recuperacao-senha/pedir/welsonlimawlt@gmail.com", HttpMethod.GET, new HttpEntity<>(null), String.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
