package br.com.welson.meucontrole.excecoes;

import br.com.welson.meucontrole.util.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RestException {

    public ForbiddenException(ErrorMessages errorMessage) {
        super(errorMessage);
    }
}
