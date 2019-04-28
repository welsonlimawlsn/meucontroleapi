package br.com.welson.meucontrole.validadores;

import br.com.welson.meucontrole.excecoes.BadRequestException;
import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.servicos.CategoriaService;

import java.time.LocalDate;

import static br.com.welson.meucontrole.util.ErrorMessages.*;

public class ValidaMovimentacao implements Validador<Movimentacao> {

    private Movimentacao movimentacao;
    private CategoriaService categoriaService;

    public ValidaMovimentacao(Movimentacao movimentacao, CategoriaService categoriaService) {
        this.movimentacao = movimentacao;
        this.categoriaService = categoriaService;
    }

    @Override
    public void validar() {
        if (movimentacao.getValor() == null || movimentacao.getValor().doubleValue() == 0) {
            throw new BadRequestException(VALOR_MOVIMENTACAO_DIFERENTE_ZERO);
        }
        if (movimentacao.getConta() == null) {
            throw new BadRequestException(MOVIMENTACAO_DEVE_ASSOCIADA_CONTA);
        }
        if (movimentacao.getDescricao() == null || movimentacao.getDescricao().isEmpty()) {
            throw new BadRequestException(DESCRICAO_MOVIMENTACAO_OBRIGATORIA);
        }
        if (movimentacao.getData() == null) {
            movimentacao.setData(LocalDate.now());
        }
        if (movimentacao.getConsolidada() == null) {
            movimentacao.setConsolidada(false);
        }
        if (movimentacao.getCategoria() == null || categoriaService.procurarPeloId(movimentacao.getCategoria().getId()) == null) {
            throw new BadRequestException(MOVIMENTACAO_DEVE_ASSOCIADA_CATEGORIA);
        }
        movimentacao.mudarSinalCasoNecessario();
    }

    @Override
    public Movimentacao getEntidade() {
        return movimentacao;
    }
}
