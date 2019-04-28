package br.com.welson.meucontrole.validadores;

import br.com.welson.meucontrole.excecoes.BadRequestException;
import br.com.welson.meucontrole.persistencia.modelos.IEntidade;
import br.com.welson.meucontrole.util.EntidadeUtil;

import static br.com.welson.meucontrole.util.ErrorMessages.ID_ENTIDADE_NAO_NULO;
import static br.com.welson.meucontrole.util.ErrorMessages.ID_ENTIDADE_NULO;

public class ValidaCamposEntidade {

    public static void validar(Validador validador, boolean idNulo) {
        if (idNulo && !EntidadeUtil.idEstaVazio((IEntidade) validador.getEntidade())) {
            throw new BadRequestException(ID_ENTIDADE_NULO);
        } else if (!idNulo && EntidadeUtil.idEstaVazio((IEntidade) validador.getEntidade())) {
            throw new BadRequestException(ID_ENTIDADE_NAO_NULO);
        }
        validador.validar();
    }
}
