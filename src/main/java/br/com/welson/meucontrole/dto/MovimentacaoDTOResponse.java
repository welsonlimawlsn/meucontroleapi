package br.com.welson.meucontrole.dto;

import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovimentacaoDTOResponse {

    private String descricao;
    protected BigDecimal valor;
    private LocalDate data;
    private ContaDTO conta;
    private CategoriaDTO categoria;
    private Boolean consolidada;

    public static MovimentacaoDTOResponse convertToDTO(Movimentacao movimentacao) {
        return new MovimentacaoDTOResponseBuilder()
                .descricao(movimentacao.getDescricao())
                .valor(movimentacao.getValor())
                .data(movimentacao.getData())
                .conta(ContaDTO.convertToDTO(movimentacao.getConta()))
                .categoria(CategoriaDTO.convertToDTO(movimentacao.getCategoria()))
                .consolidada(movimentacao.getConsolidada()).build();
    }
}
