package br.com.welson.meucontrole.excecoes;

import br.com.welson.meucontrole.util.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RestException {

    public NotFoundException(ErrorMessages errorMessage) {
        super(errorMessage);
    }
}
