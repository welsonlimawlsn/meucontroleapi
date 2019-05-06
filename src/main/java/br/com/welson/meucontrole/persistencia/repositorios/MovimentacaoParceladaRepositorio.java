package br.com.welson.meucontrole.persistencia.repositorios;

import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovimentacaoParceladaRepositorio extends PagingAndSortingRepository<MovimentacaoParcelada, String> {
}
