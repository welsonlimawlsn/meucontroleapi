package br.com.welson.meucontrole.servicos.implemetacoes;

import br.com.welson.meucontrole.persistencia.modelos.Saldo;
import br.com.welson.meucontrole.servicos.FormaSaldo;
import br.com.welson.meucontrole.servicos.SaldoService;
import org.springframework.stereotype.Service;

@Service
public class SaldoServiceImpl implements SaldoService {

    @Override
    public Saldo getSaldo(FormaSaldo formaSaldo) {
        return new Saldo(formaSaldo.calcularSaldo());
    }
}
