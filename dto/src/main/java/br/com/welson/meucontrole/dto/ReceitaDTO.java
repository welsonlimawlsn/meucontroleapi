package br.com.welson.meucontrole.dto;

import br.com.welson.meucontrole.persistencia.modelos.Categoria;
import br.com.welson.meucontrole.persistencia.modelos.Receita;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ReceitaDTO implements DTO<Receita> {

    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private Categoria categoria;
    private Boolean consolidada;

    @Override
    public Receita convertToObject() {
        return new Receita(descricao, valor, data, categoria, consolidada);
    }

}
