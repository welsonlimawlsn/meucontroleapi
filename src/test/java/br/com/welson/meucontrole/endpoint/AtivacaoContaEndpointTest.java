package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.persistencia.modelos.AtivacaoConta;
import br.com.welson.meucontrole.persistencia.repositorios.AtivacaoContaRepositorio;
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
public class AtivacaoContaEndpointTest {

    @MockBean
    private UsuarioRepositorio usuarioRepositorio;

    @MockBean
    private AtivacaoContaRepositorio ativacaoContaRepositorio;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private AtivacaoConta ativacaoConta = mockAtivacaoConta();

    public static AtivacaoConta mockAtivacaoConta() {
        return new AtivacaoConta("B7AD2350C2A4C394373E59786F814E32D91AEE9604A5B57DC7023CF1074694E4", UsuarioEndpointTest.mockUsuario());
    }

    @Before
    public void setup() {
        BDDMockito.when(ativacaoContaRepositorio.findByHash(ativacaoConta.getHash())).thenReturn(Optional.of(ativacaoConta));
        BDDMockito.when(usuarioRepositorio.findById(ativacaoConta.getUsuario().getId())).thenReturn(Optional.of(ativacaoConta.getUsuario()));
    }

    @Test
    public void ativarContaHashValidoDeveRetornaOk() {
        ResponseEntity<String> exchange = testRestTemplate.exchange("/ativacao-conta/" + ativacaoConta.getHash(), HttpMethod.GET, new HttpEntity<>(null), String.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void ativarContaHashNullDeveRetornaNotFound() {
        ResponseEntity<String> exchange = testRestTemplate.exchange("/ativacao-conta/", HttpMethod.GET, new HttpEntity<>(null), String.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void ativarContaHashInvalidoDeveRetornaBadRequest() {
        ResponseEntity<String> exchange = testRestTemplate.exchange("/ativacao-conta/123456789", HttpMethod.GET, new HttpEntity<>(null), String.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}
