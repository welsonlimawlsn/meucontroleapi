package br.com.welson.meucontrole.util;

import javax.servlet.http.HttpServletResponse;

import static br.com.welson.meucontrole.seguranca.filtros.Constantes.HEADER_STRING;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

public class HeaderUtil {

    private HeaderUtil() {
    }

    public static void adicionarTokenAoHeader(HttpServletResponse response, Token token) {
        response.addHeader("Content-Type", APPLICATION_JSON_UTF8_VALUE);
        response.addHeader(HEADER_STRING, token.getToken());
    }
}
