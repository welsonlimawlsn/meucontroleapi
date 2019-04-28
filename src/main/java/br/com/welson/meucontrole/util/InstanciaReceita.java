package br.com.welson.meucontrole.util;

import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;
import br.com.welson.meucontrole.persistencia.modelos.Receita;

import java.time.LocalDate;

public class InstanciaReceita implements MovimentacaoIntanciavel {
    @Override
    public Movimentacao getInstance(MovimentacaoParcelada movimentacaoParcelada, LocalDate data) {
        return new Receita(movimentacaoParcelada.getDescricao(), movimentacaoParcelada.getValor(), data, movimentacaoParcelada.getConta(), movimentacaoParcelada.getCategoria(), false, movimentacaoParcelada);
    }
}
