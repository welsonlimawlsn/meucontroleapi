package br.com.welson.meucontrole.validadores;

public interface Validador<T> {

    void validar();

    T getEntidade();
}
