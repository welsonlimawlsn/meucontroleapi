package br.com.welson.meucontrole.servicos.implemetacoes;

import br.com.welson.meucontrole.persistencia.modelos.Categoria;
import br.com.welson.meucontrole.persistencia.modelos.Conta;
import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;
import br.com.welson.meucontrole.persistencia.repositorios.MovimentacaoRepositorio;
import br.com.welson.meucontrole.servicos.CrudService;
import br.com.welson.meucontrole.servicos.MovimentacaoService;
import br.com.welson.meucontrole.util.MovimentacaoIntanciavel;
import br.com.welson.meucontrole.util.UsuarioUtil;
import br.com.welson.meucontrole.validadores.ValidaCamposEntidade;
import br.com.welson.meucontrole.validadores.ValidaMovimentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class MovimentacaoServiceImpl implements MovimentacaoService {

    private final MovimentacaoRepositorio movimentacaoRepositorio;
    private final CrudService<Conta> contaCrudService;
    private final CrudService<MovimentacaoParcelada> movimentacaoParceladaCrudService;
    private final CrudService<Categoria> categoriaCrudService;

    @Autowired
    public MovimentacaoServiceImpl(MovimentacaoRepositorio movimentacaoRepositorio, CrudService<Conta> contaCrudService, CrudService<MovimentacaoParcelada> movimentacaoParceladaCrudService, CrudService<Categoria> categoriaCrudService) {
        this.movimentacaoRepositorio = movimentacaoRepositorio;
        this.contaCrudService = contaCrudService;
        this.movimentacaoParceladaCrudService = movimentacaoParceladaCrudService;
        this.categoriaCrudService = categoriaCrudService;
    }

    @Override
    public Movimentacao criar(Movimentacao movimentacao, Long idConta) {
        Conta conta = contaCrudService.procurarPeloId(idConta);
        movimentacao.setConta(conta);
        return criar(movimentacao);
    }

    @Override
    public MovimentacaoParcelada criar(MovimentacaoParcelada movimentacaoParcelada, Long idConta, MovimentacaoIntanciavel movimentacaoIntanciavel) {
        Conta conta = contaCrudService.procurarPeloId(idConta);
        movimentacaoParcelada.setConta(conta);
        movimentacaoParcelada.setMovimentacoes(new ArrayList<>());
        movimentacaoParcelada = movimentacaoParceladaCrudService.criar(movimentacaoParcelada);
        LocalDate data = movimentacaoParcelada.getDataInicial();
        for (int i = 0; i < movimentacaoParcelada.getQuantidadeParcelas(); i++) {
            Movimentacao movimentacao = movimentacaoIntanciavel.getInstance(movimentacaoParcelada, data);
            movimentacaoParcelada.getMovimentacoes().add(criar(movimentacao));
            data = data.plusMonths(1);
        }
        return movimentacaoParcelada;
    }

    @Override
    public Movimentacao procurarPeloId(Long id) {
        return null;
    }

    @Override
    public Movimentacao criar(Movimentacao entidade) {
        UsuarioUtil.verificarSeContaPerteceAoUsuarioLogado(entidade.getConta());
        ValidaCamposEntidade.validar(new ValidaMovimentacao(entidade, categoriaCrudService), true);
        return movimentacaoRepositorio.save(entidade);
    }

    @Override
    public Movimentacao alterar(Movimentacao entidade) {
        return null;
    }

    @Override
    public void apagar(Movimentacao entidade) {

    }

    @Override
    public Iterable<Movimentacao> listarTodos() {
        return null;
    }
}
