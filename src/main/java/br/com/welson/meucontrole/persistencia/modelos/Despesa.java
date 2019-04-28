package br.com.welson.meucontrole.persistencia.modelos;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_despesas")
public class Despesa extends Movimentacao {

    public Despesa() {
    }

    public Despesa(String descricao, BigDecimal valor, LocalDate data, Conta conta, Categoria categoria, Boolean consolidada, MovimentacaoParcelada movimentacaoParcelada) {
        super(descricao, valor, data, conta, categoria, consolidada, movimentacaoParcelada);
    }

    public Despesa(String descricao, BigDecimal valor, LocalDate data, Categoria categoria, Boolean consolidada) {
        super(descricao, valor, data, categoria, consolidada);
    }

    @Override
    public void mudarSinalCasoNecessario() {
        if (valor.doubleValue() > 0) {
            mudarSinal();
        }
    }
}
