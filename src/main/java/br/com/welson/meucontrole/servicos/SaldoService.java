package br.com.welson.meucontrole.servicos;

import br.com.welson.meucontrole.persistencia.modelos.Saldo;

public interface SaldoService {

    Saldo getSaldo(FormaSaldo formaSaldo);
}
