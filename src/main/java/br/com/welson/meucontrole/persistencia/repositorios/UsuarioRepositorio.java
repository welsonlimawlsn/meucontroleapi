package br.com.welson.meucontrole.persistencia.repositorios;

import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends PagingAndSortingRepository<Usuario, Long> {

    Optional<Usuario> findByUsuario(String usuario);

    Optional<Usuario> findByEmail(String email);
}
