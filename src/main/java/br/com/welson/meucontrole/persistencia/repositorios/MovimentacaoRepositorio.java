package br.com.welson.meucontrole.persistencia.repositorios;

import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovimentacaoRepositorio extends PagingAndSortingRepository<Movimentacao, String> {

    List<Movimentacao> findByConta_UsuarioAndDataBetween(Usuario usuario, LocalDate inicio, LocalDate fim);

}
