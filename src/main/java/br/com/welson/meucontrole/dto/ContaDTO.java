package br.com.welson.meucontrole.dto;

import br.com.welson.meucontrole.persistencia.modelos.Conta;

public class ContaDTO implements DTO<Conta> {

    private String nome;

    public ContaDTO() {
    }

    public ContaDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public Conta convertToObject() {
        return new Conta(nome);
    }
}
