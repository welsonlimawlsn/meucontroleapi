package br.com.welson.meucontrole.persistencia.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IEntidade<T> {

    @JsonIgnore
    T getIdentificador();

    @JsonIgnore
    void setIdentificador(T id);
}
