package br.com.welson.meucontrole.servicos.implemetacoes;

import br.com.welson.meucontrole.persistencia.modelos.Conta;
import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;
import br.com.welson.meucontrole.persistencia.repositorios.MovimentacaoRepositorio;
import br.com.welson.meucontrole.servicos.CategoriaService;
import br.com.welson.meucontrole.servicos.ContaService;
import br.com.welson.meucontrole.servicos.MovimentacaoParceladaService;
import br.com.welson.meucontrole.servicos.MovimentacaoService;
import br.com.welson.meucontrole.util.MovimentacaoIntanciavel;
import br.com.welson.meucontrole.util.UsuarioUtil;
import br.com.welson.meucontrole.validadores.ValidaCamposEntidade;
import br.com.welson.meucontrole.validadores.ValidaMovimentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovimentacaoServiceImpl implements MovimentacaoService {

    private final MovimentacaoRepositorio movimentacaoRepositorio;
    private final ContaService contaService;
    private final MovimentacaoParceladaService movimentacaoParceladaService;
    private final CategoriaService categoriaService;

    @Autowired
    public MovimentacaoServiceImpl(MovimentacaoRepositorio movimentacaoRepositorio, ContaService contaService, MovimentacaoParceladaService movimentacaoParceladaService, CategoriaService categoriaService) {
        this.movimentacaoRepositorio = movimentacaoRepositorio;
        this.contaService = contaService;
        this.movimentacaoParceladaService = movimentacaoParceladaService;
        this.categoriaService = categoriaService;
    }

    @Override
    public Movimentacao criar(Movimentacao movimentacao, Long idConta) {
        Conta conta = contaService.procurarPeloId(idConta);
        movimentacao.setConta(conta);
        return criar(movimentacao);
    }

    @Override
    public MovimentacaoParcelada criar(MovimentacaoParcelada movimentacaoParcelada, Long idConta, MovimentacaoIntanciavel movimentacaoIntanciavel) {
        Conta conta = contaService.procurarPeloId(idConta);
        movimentacaoParcelada.setConta(conta);
        movimentacaoParcelada.setMovimentacoes(new ArrayList<>());
        movimentacaoParcelada = movimentacaoParceladaService.criar(movimentacaoParcelada);
        LocalDate data = movimentacaoParcelada.getDataInicial();
        for (int i = 0; i < movimentacaoParcelada.getQuantidadeParcelas(); i++) {
            Movimentacao movimentacao = movimentacaoIntanciavel.getInstance(movimentacaoParcelada, data);
            movimentacaoParcelada.getMovimentacoes().add(criar(movimentacao));
            data = data.plusMonths(1);
        }
        return movimentacaoParcelada;
    }

    @Override
    public List<Movimentacao> getMovimentacoesEntre(LocalDate inicio, LocalDate fim) {
        return movimentacaoRepositorio.findByConta_UsuarioAndDataBetween(UsuarioUtil.getUsuarioLogado(), inicio, fim);
    }

    @Override
    public Movimentacao procurarPeloId(Long id) {
        return null;
    }

    @Override
    public Movimentacao criar(Movimentacao entidade) {
        UsuarioUtil.verificarSeContaPerteceAoUsuarioLogado(entidade.getConta());
        ValidaCamposEntidade.validar(new ValidaMovimentacao(entidade, categoriaService), true);
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
