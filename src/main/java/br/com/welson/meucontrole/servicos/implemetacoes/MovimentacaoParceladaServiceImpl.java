package br.com.welson.meucontrole.servicos.implemetacoes;

import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;
import br.com.welson.meucontrole.persistencia.repositorios.MovimentacaoParceladaRepositorio;
import br.com.welson.meucontrole.servicos.MovimentacaoParceladaService;
import br.com.welson.meucontrole.util.UsuarioUtil;
import br.com.welson.meucontrole.validadores.ValidaCamposEntidade;
import br.com.welson.meucontrole.validadores.ValidaMovimentacaoParcelada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoParceladaServiceImpl implements MovimentacaoParceladaService {

    private final MovimentacaoParceladaRepositorio movimentacaoParceladaRepositorio;

    @Autowired
    public MovimentacaoParceladaServiceImpl(MovimentacaoParceladaRepositorio movimentacaoParceladaRepositorio) {
        this.movimentacaoParceladaRepositorio = movimentacaoParceladaRepositorio;
    }

    @Override
    public MovimentacaoParcelada procurarPeloId(Long id) {
        return null;
    }

    @Override
    public MovimentacaoParcelada criar(MovimentacaoParcelada entidade) {
        UsuarioUtil.verificarSeContaPerteceAoUsuarioLogado(entidade.getConta());
        ValidaCamposEntidade.validar(new ValidaMovimentacaoParcelada(entidade), true);
        return movimentacaoParceladaRepositorio.save(entidade);
    }

    @Override
    public MovimentacaoParcelada alterar(MovimentacaoParcelada entidade) {
        return null;
    }

    @Override
    public void apagar(MovimentacaoParcelada entidade) {

    }

    @Override
    public Iterable<MovimentacaoParcelada> listarTodos() {
        return null;
    }

}
