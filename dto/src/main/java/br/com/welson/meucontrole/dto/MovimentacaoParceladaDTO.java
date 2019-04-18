package br.com.welson.meucontrole.dto;

import br.com.welson.meucontrole.persistencia.modelos.Categoria;
import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MovimentacaoParceladaDTO implements DTO<MovimentacaoParcelada> {

    private String descricao;
    private BigDecimal valor;
    private LocalDate dataInicial;
    private Categoria categoria;
    private Integer quantidadeParcelas;
    private List<Movimentacao> movimentacoes;

    @Override
    public MovimentacaoParcelada convertToObject() {
        return new MovimentacaoParcelada(descricao, valor, dataInicial, categoria, quantidadeParcelas, movimentacoes);
    }

}
