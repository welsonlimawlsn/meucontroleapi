package br.com.welson.meucontrole.persistencia.modelos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Saldo {

    private BigDecimal saldo;

    public Saldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

}
