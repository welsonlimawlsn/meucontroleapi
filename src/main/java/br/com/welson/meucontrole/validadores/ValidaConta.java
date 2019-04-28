package br.com.welson.meucontrole.validadores;

import br.com.welson.meucontrole.excecoes.BadRequestException;
import br.com.welson.meucontrole.persistencia.modelos.Conta;
import br.com.welson.meucontrole.util.UsuarioUtil;

import static br.com.welson.meucontrole.util.ErrorMessages.NOME_CONTA_OBRIGATORIO;

public class ValidaConta implements Validador<Conta> {

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
            throw new BadRequestException(NOME_CONTA_OBRIGATORIO);
        }
    }

    @Override
    public Conta getEntidade() {
        return conta;
    }
}
