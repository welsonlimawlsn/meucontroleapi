package br.com.welson.meucontrole.servicos.implemetacoes;

import br.com.welson.meucontrole.excecoes.NotFoundException;
import br.com.welson.meucontrole.persistencia.modelos.Conta;
import br.com.welson.meucontrole.persistencia.repositorios.ContaRepositorio;
import br.com.welson.meucontrole.servicos.ContaService;
import br.com.welson.meucontrole.util.UsuarioUtil;
import br.com.welson.meucontrole.validadores.ValidaCamposEntidade;
import br.com.welson.meucontrole.validadores.ValidaConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.welson.meucontrole.util.ErrorMessages.CONTA_NAO_ENCONTRADA;

@Service
public class ContaServiceImpl implements ContaService {

    private final ContaRepositorio contaRepositorio;

    @Autowired
    public ContaServiceImpl(ContaRepositorio contaRepositorio) {
        this.contaRepositorio = contaRepositorio;
    }

    @Override
    public Conta procurarPeloId(String id) {
        return contaRepositorio.findByIdAndUsuario(id, UsuarioUtil.getUsuarioLogado())
                .orElseThrow(() -> new NotFoundException(CONTA_NAO_ENCONTRADA));
    }

    @Override
    public Conta criar(Conta entidade) {
        ValidaCamposEntidade.validar(new ValidaConta(entidade), true);
        return contaRepositorio.save(entidade);
    }

    @Override
    public Conta alterar(Conta entidade) {
        return null;
    }

    @Override
    public void apagar(Conta entidade) {

    }

    @Override
    public Iterable<Conta> listarTodos() {
        return contaRepositorio.findByUsuario(UsuarioUtil.getUsuarioLogado());
    }

}
