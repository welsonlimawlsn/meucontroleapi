package br.com.welson.meucontrole.servicos;

import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;
import br.com.welson.meucontrole.util.MovimentacaoIntanciavel;

import java.time.LocalDate;
import java.util.List;

public interface MovimentacaoService extends CrudService<Movimentacao, String> {

    Movimentacao criar(Movimentacao receita, String idConta);

    MovimentacaoParcelada criar(MovimentacaoParcelada movimentacaoParcelada, String idConta, MovimentacaoIntanciavel movimentacaoIntanciavel);

    List<Movimentacao> getMovimentacoesEntre(LocalDate inicio, LocalDate fim);
}
