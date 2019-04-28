package br.com.welson.meucontrole.validadores;

import br.com.welson.meucontrole.excecoes.BadRequestException;
import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;

import static br.com.welson.meucontrole.util.ErrorMessages.*;

public class ValidaMovimentacaoParcelada implements Validador<MovimentacaoParcelada> {

    private MovimentacaoParcelada movimentacaoParcelada;

    public ValidaMovimentacaoParcelada(MovimentacaoParcelada movimentacaoParcelada) {
        this.movimentacaoParcelada = movimentacaoParcelada;
    }

    @Override
    public void validar() {
        if (movimentacaoParcelada.getValor() == null || movimentacaoParcelada.getValor().doubleValue() == 0) {
            throw new BadRequestException(VALOR_MOVIMENTACAO_DIFERENTE_ZERO);
        }
        if (movimentacaoParcelada.getConta() == null) {
            throw new BadRequestException(MOVIMENTACAO_DEVE_ASSOCIADA_CONTA);
        }
        if (movimentacaoParcelada.getDescricao() == null || movimentacaoParcelada.getDescricao().isEmpty()) {
            throw new BadRequestException(DESCRICAO_MOVIMENTACAO_OBRIGATORIA);
        }
        if (movimentacaoParcelada.getDataInicial() == null) {
            throw new BadRequestException(DATA_INICIAL_MOVIMENTACAO_OBRIGATORIA);
        }
        if (movimentacaoParcelada.getQuantidadeParcelas() == null || movimentacaoParcelada.getQuantidadeParcelas() <= 1) {
            throw new BadRequestException(MOVIMENTACAO_PRECISA_MAIS_DE_UMA_PARCELA);
        }
    }

    @Override
    public MovimentacaoParcelada getEntidade() {
        return movimentacaoParcelada;
    }
}
