package br.com.welson.meucontrole.persistencia.repositorios;

import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovimentacaoRepositorio extends PagingAndSortingRepository<Movimentacao, Long> {

}
