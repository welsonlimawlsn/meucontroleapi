package br.com.welson.meucontrole.persistencia.repositorios;

import br.com.welson.meucontrole.persistencia.modelos.Conta;
import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ContaRepositorio extends PagingAndSortingRepository<Conta, Long> {

    Iterable<Conta> findByUsuario(Usuario usuario);

    Optional<Conta> findByIdAndUsuario(Long id, Usuario usuario);
}
