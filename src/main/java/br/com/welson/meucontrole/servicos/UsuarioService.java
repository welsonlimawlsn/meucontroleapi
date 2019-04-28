package br.com.welson.meucontrole.servicos;

import br.com.welson.meucontrole.persistencia.modelos.Usuario;

public interface UsuarioService extends CrudService<Usuario> {

    void ativarConta(String hash);

    Usuario getUsuarioPorEmail(String email);

}
