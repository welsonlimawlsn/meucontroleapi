package br.com.welson.meucontrole.excecoes;

import br.com.welson.meucontrole.util.ErrorMessages;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(code = BAD_REQUEST)
public class BadRequestException extends RestException {

    public BadRequestException(ErrorMessages errorMessage) {
        super(errorMessage);
    }
}
