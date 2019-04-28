package br.com.welson.meucontrole.excecoes;

import br.com.welson.meucontrole.util.ErrorMessages;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class RestException extends RuntimeException {

    private ErrorMessages errorMessage;

    @Override
    public String getMessage() {
        return errorMessage.getMessage();
    }
}
