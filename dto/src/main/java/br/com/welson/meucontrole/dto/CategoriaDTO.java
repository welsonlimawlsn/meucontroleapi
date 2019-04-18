package br.com.welson.meucontrole.dto;

import br.com.welson.meucontrole.persistencia.modelos.Categoria;

public class CategoriaDTO implements DTO<Categoria> {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public Categoria convertToObject() {
        return new Categoria(nome);
    }
}
