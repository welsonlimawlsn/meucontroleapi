package br.com.welson.meucontrole.excecoes;

import br.com.welson.meucontrole.util.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RestException {

    public InternalServerErrorException(ErrorMessages errorMessage) {
        super(errorMessage);
    }
}
