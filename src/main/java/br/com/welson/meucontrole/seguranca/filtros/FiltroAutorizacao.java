package br.com.welson.meucontrole.seguranca.filtros;

import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import br.com.welson.meucontrole.servicos.implemetacoes.UserDetailsServiceImpl;
import br.com.welson.meucontrole.util.HeaderUtil;
import br.com.welson.meucontrole.util.Token;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static br.com.welson.meucontrole.seguranca.filtros.Constantes.HEADER_STRING;
import static br.com.welson.meucontrole.seguranca.filtros.Constantes.PREFIXO_TOKEN;

public class FiltroAutorizacao extends BasicAuthenticationFilter {

    private final UserDetailsService userDetailsService;

    public FiltroAutorizacao(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if (header != null && header.startsWith(PREFIXO_TOKEN)) {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
            if (authenticationToken != null) {
                HeaderUtil.adicionarTokenAoHeader(response, Token.getNewToken((Usuario) authenticationToken.getPrincipal()));
            }
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        Token token = Token.parse(request.getHeader(HEADER_STRING));
        UserDetails userDetails = userDetailsService.loadUserByUsername(token.getUsuario());
        Usuario usuario = ((UserDetailsServiceImpl) userDetailsService).carregarUsuarioPorNomeUsuario(token.getUsuario());
        return token.getUsuario() != null ? new UsernamePasswordAuthenticationToken(usuario, null, userDetails.getAuthorities()) : null;
    }
}
