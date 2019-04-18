package br.com.welson.meucontrole.servicos;

import br.com.welson.meucontrole.persistencia.modelos.Entidade;

public interface CrudService<T extends Entidade> {

    T procurarPeloId(Long id);

    T criar(T entidade);

    T alterar(T entidade);

    void apagar(T entidade);

    Iterable<T> listarTodos();
}
