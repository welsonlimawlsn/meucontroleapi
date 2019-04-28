package br.com.welson.meucontrole.util;

import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MovimentacaoUtil {

    public static BigDecimal calculaSaldoMovimentacoes(List<Movimentacao> movimentacoes, Predicate<Movimentacao> regraData) {
        return movimentacoes.stream()
                .filter(regraData)
                .map(Movimentacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal calculaSaldoMovimentacoesParceladas(List<MovimentacaoParcelada> movimentacaoParceladas, Predicate<MovimentacaoParcelada> regraData, Predicate<Movimentacao> regraDataMovimentacao) {
        List<Movimentacao> movimentacaoList = movimentacaoParceladas.stream()
                .filter(regraData)
                .map(MovimentacaoParcelada::getMovimentacoes)
                .reduce((movimentacoes1, movimentacoes2) -> {
                    movimentacoes1.addAll(movimentacoes2);
                    return movimentacoes1;
                }).orElse(new ArrayList<>());
        return calculaSaldoMovimentacoes(movimentacaoList, regraDataMovimentacao);
    }
}
