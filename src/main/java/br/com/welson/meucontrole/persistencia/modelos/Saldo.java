package br.com.welson.meucontrole.persistencia.modelos;

import java.math.BigDecimal;

public class Saldo {

    private BigDecimal saldo;

    public Saldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
