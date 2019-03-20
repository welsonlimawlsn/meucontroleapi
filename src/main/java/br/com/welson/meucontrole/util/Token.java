package br.com.welson.meucontrole.util;

import br.com.welson.meucontrole.excecoes.ForbiddenException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.ZonedDateTime;
import java.util.Date;

import static br.com.welson.meucontrole.seguranca.filtros.Constantes.CHAVE;
import static br.com.welson.meucontrole.seguranca.filtros.Constantes.PREFIXO_TOKEN;

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
            throw new ForbiddenException("Seu token expirou! Faça o login novamente.");
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiracao() {
        return expiracao;
    }

    public void setExpiracao(String expiracao) {
        this.expiracao = expiracao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
