package br.com.welson.meucontrole.dto;

import br.com.welson.meucontrole.persistencia.modelos.Conta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContaDTO implements DTO<Conta> {

    private String nome;

    public ContaDTO() {
    }


    @Override
    public Conta convertToObject() {
        return new Conta(nome);
    }

    public static ContaDTO convertToDTO(Conta conta) {
        return new ContaDTO(conta.getNome());
    }
}
