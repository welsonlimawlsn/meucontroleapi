package br.com.welson.meucontrole.dto;

import br.com.welson.meucontrole.persistencia.modelos.Categoria;
import br.com.welson.meucontrole.persistencia.modelos.Despesa;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DespesaDTO implements DTO<Despesa> {

    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private Categoria categoria;
    private Boolean consolidada;

    @Override
    public Despesa convertToObject() {
        return new Despesa(descricao, valor, data, categoria, consolidada);
    }

}
