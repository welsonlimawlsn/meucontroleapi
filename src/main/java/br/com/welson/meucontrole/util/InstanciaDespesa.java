package br.com.welson.meucontrole.util;

import br.com.welson.meucontrole.persistencia.modelos.Despesa;
import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;

import java.time.LocalDate;

public class InstanciaDespesa implements MovimentacaoIntanciavel {
    @Override
    public Movimentacao getInstance(MovimentacaoParcelada movimentacaoParcelada, LocalDate data) {
        return new Despesa(movimentacaoParcelada.getDescricao(), movimentacaoParcelada.getValor(), data, movimentacaoParcelada.getConta(), movimentacaoParcelada.getCategoria(), false, movimentacaoParcelada);
    }
}
