package br.com.welson.meucontrole.servicos;

import br.com.welson.meucontrole.persistencia.modelos.AtivacaoConta;
import br.com.welson.meucontrole.persistencia.modelos.Usuario;

public interface AtivacaoContaService extends CrudService<AtivacaoConta, String> {

    AtivacaoConta criar(Usuario usuario);

    AtivacaoConta pegarAtivacaoConta(String hash);
}
