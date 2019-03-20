package br.com.welson.meucontrole.util;

import br.com.welson.meucontrole.persistencia.modelos.Entidade;

public class EntidadeUtil {

    public static boolean idEstaVazio(Entidade entidade) {
        return entidade.getId() == null;
    }
}
