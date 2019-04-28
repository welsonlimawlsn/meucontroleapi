package br.com.welson.meucontrole.validadores;

import br.com.welson.meucontrole.persistencia.modelos.Entidade;

public interface Validador {

    void validar();

    Entidade getEntidade();
}
