package br.com.welson.meucontrole.persistencia.repositorios;

import br.com.welson.meucontrole.persistencia.modelos.RecuperacaoSenha;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RecuperacaoSenhaRepositorio extends PagingAndSortingRepository<RecuperacaoSenha, Long> {

    Optional<RecuperacaoSenha> findByHash(String hash);
}
