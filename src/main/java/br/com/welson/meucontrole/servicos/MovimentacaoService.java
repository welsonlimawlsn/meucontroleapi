package br.com.welson.meucontrole.servicos;

import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;
import br.com.welson.meucontrole.util.MovimentacaoIntanciavel;

public interface MovimentacaoService extends CrudService<Movimentacao, Long> {

    Movimentacao criar(Movimentacao receita, Long idConta);

    MovimentacaoParcelada criar(MovimentacaoParcelada movimentacaoParcelada, Long idConta, MovimentacaoIntanciavel movimentacaoIntanciavel);
}
