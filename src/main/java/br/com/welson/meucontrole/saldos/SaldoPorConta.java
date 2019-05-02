package br.com.welson.meucontrole.saldos;

import br.com.welson.meucontrole.persistencia.modelos.Conta;
import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.servicos.FormaSaldo;
import br.com.welson.meucontrole.util.MovimentacaoUtil;
import br.com.welson.meucontrole.util.UsuarioUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.function.Predicate;

public class SaldoPorConta implements FormaSaldo {

    private Conta conta;
    private LocalDate dataFinal;
    private boolean ateFimDoMes;

    private SaldoPorConta(Conta conta, LocalDate dataFinal, boolean ateFimDoMes) {
        this.conta = conta;
        this.dataFinal = dataFinal;
        this.ateFimDoMes = ateFimDoMes;
    }

    public SaldoPorConta(Conta conta) {
        this(conta, null, false);
    }

    public SaldoPorConta(Conta conta, LocalDate dataFinal) {
        this(conta, dataFinal, false);
    }

    public SaldoPorConta(Conta conta, boolean ateFimDoMes) {
        this(conta, null, ateFimDoMes);
    }

    @Override
    public BigDecimal calcularSaldo() {
        List<Conta> contas = UsuarioUtil.getUsuarioLogado().getContas();
        if (contas.contains(conta)) {
            Conta conta = contas.get(contas.indexOf(this.conta));
            return MovimentacaoUtil.calculaSaldoMovimentacoes(conta.getMovimentacoes(), getRegraDataMovimentacao());
        }
        return null;
    }

    private Predicate<Movimentacao> getRegraDataMovimentacao() {
        return movimentacao -> movimentacao.getData().isBefore(getDataFinal()) || movimentacao.getData().isEqual(LocalDate.now());
    }

    private LocalDate getDataFinal() {
        if (dataFinal != null)
            return dataFinal;
        if (ateFimDoMes)
            return LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        return LocalDate.now();
    }
}
