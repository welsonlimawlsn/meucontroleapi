package br.com.welson.meucontrole.seguranca.filtros;

import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import br.com.welson.meucontrole.util.HeaderUtil;
import br.com.welson.meucontrole.util.Token;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FiltroAutenticacao extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public FiltroAutenticacao(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsuario(), usuario.getSenha()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        Token token = Token.getNewToken((Usuario) authResult.getPrincipal());
        HeaderUtil.adicionarTokenAoHeader(response, token);
        new ObjectMapper().writeValue(response.getWriter(), token);
    }
}
