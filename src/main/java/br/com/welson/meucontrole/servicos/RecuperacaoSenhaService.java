package br.com.welson.meucontrole.servicos;

import br.com.welson.meucontrole.persistencia.modelos.NovaSenha;
import br.com.welson.meucontrole.persistencia.modelos.RecuperacaoSenha;

public interface RecuperacaoSenhaService extends CrudService<RecuperacaoSenha, String> {

    RecuperacaoSenha getRecuperacaoSenhaSeExistir(String hash);

    boolean recuperarSenha(String hash, NovaSenha novaSenha);

    RecuperacaoSenha criar(String email);
}
