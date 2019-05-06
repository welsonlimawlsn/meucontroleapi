package br.com.welson.meucontrole.persistencia.repositorios;

import br.com.welson.meucontrole.persistencia.modelos.Categoria;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoriaRepositorio extends PagingAndSortingRepository<Categoria, String> {
}
