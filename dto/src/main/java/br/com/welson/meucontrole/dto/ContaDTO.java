package br.com.welson.meucontrole.dto;

import br.com.welson.meucontrole.persistencia.modelos.Conta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaDTO implements DTO<Conta> {

    private String nome;

    public ContaDTO() {
    }


    @Override
    public Conta convertToObject() {
        return new Conta(nome);
    }
}
