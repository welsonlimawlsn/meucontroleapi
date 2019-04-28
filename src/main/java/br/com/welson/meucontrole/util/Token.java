package br.com.welson.meucontrole.util;

import br.com.welson.meucontrole.excecoes.ForbiddenException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Date;

import static br.com.welson.meucontrole.seguranca.filtros.Constantes.CHAVE;
import static br.com.welson.meucontrole.seguranca.filtros.Constantes.PREFIXO_TOKEN;
import static br.com.welson.meucontrole.util.ErrorMessages.TOKEN_EXPIRADO;

@Getter
@Setter
public class Token {

    private String token;
    private String expiracao;
    @JsonIgnore
    private String usuario;

    public Token(String usuario, ZonedDateTime expiracao) {
        this.expiracao = expiracao.toString();
        this.usuario = usuario;
        gerarToken(usuario, Date.from(expiracao.toInstant()));
    }

    private Token(String token, String usuario) {
        this.token = token;
        this.usuario = usuario;
    }

    private void gerarToken(String usuario, Date expiracao) {
        token = PREFIXO_TOKEN + Jwts.builder().setSubject(usuario).setExpiration(expiracao).signWith(SignatureAlgorithm.HS256, CHAVE)
                .compact();
    }

    public static Token parse(String token) {
        try {
            Claims body = Jwts.parser().setSigningKey(CHAVE).parseClaimsJws(token.replace(PREFIXO_TOKEN, "")).getBody();
            return new Token(token, body.getSubject());
        } catch (ExpiredJwtException e) {
            throw new ForbiddenException(TOKEN_EXPIRADO);
        }
    }
}
