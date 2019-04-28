package br.com.welson.meucontrole.util;

import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;

import java.time.LocalDate;

public interface MovimentacaoIntanciavel {

    Movimentacao getInstance(MovimentacaoParcelada movimentacaoParcelada, LocalDate data);
}
