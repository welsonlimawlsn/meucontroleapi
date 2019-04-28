package br.com.welson.meucontrole.servicos;

public interface CrudService<T, ID> {

    T procurarPeloId(ID id);

    T criar(T entidade);

    T alterar(T entidade);

    void apagar(T entidade);

    Iterable<T> listarTodos();
}
