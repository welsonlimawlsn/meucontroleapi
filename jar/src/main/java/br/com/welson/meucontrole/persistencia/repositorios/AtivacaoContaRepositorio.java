package br.com.welson.meucontrole.persistencia.repositorios;

import br.com.welson.meucontrole.persistencia.modelos.AtivacaoConta;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AtivacaoContaRepositorio extends PagingAndSortingRepository<AtivacaoConta, Long> {

    Optional<AtivacaoConta> findByHash(String hash);
}
