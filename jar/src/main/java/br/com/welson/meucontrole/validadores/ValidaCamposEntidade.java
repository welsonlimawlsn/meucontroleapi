package br.com.welson.meucontrole.validadores;

import br.com.welson.meucontrole.excecoes.BadRequestException;
import br.com.welson.meucontrole.util.EntidadeUtil;

public class ValidaCamposEntidade {

    public static void validar(Validador validador, boolean idNulo) {
        if (idNulo && !EntidadeUtil.idEstaVazio(validador.getEntidade())) {
            throw new BadRequestException("Você não pode recriar essa entidade!");
        } else if (!idNulo && EntidadeUtil.idEstaVazio(validador.getEntidade())) {
            throw new BadRequestException("Entidade invalida");
        }
        validador.validar();
    }
}
