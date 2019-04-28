package br.com.welson.meucontrole.validadores;

import br.com.welson.meucontrole.excecoes.BadRequestException;
import br.com.welson.meucontrole.persistencia.modelos.Conta;
import br.com.welson.meucontrole.persistencia.modelos.Entidade;
import br.com.welson.meucontrole.util.UsuarioUtil;

public class ValidaConta implements Validador {

    private Conta conta;

    public ValidaConta(Conta conta) {
        this.conta = conta;
    }

    @Override
    public void validar() {
        if (conta.getUsuario() == null) {
            conta.setUsuario(UsuarioUtil.getUsuarioLogado());
        }
        if (conta.getNome() == null) {
            throw new BadRequestException("Um nome para a conta é obrigatório");
        }
    }

    @Override
    public Entidade getEntidade() {
        return conta;
    }
}
