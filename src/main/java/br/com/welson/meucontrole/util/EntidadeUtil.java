package br.com.welson.meucontrole.util;

import br.com.welson.meucontrole.persistencia.modelos.IEntidade;

public class EntidadeUtil {

    public static boolean idEstaVazio(IEntidade entidade) {
        return entidade.getIdentificador() == null;
    }
}
