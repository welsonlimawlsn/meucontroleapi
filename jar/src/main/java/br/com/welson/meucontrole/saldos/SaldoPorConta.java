package br.com.welson.meucontrole.saldos;

import br.com.welson.meucontrole.persistencia.modelos.Conta;
import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;
import br.com.welson.meucontrole.servicos.FormaSaldo;
import br.com.welson.meucontrole.util.MovimentacaoUtil;
import br.com.welson.meucontrole.util.UsuarioUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public class SaldoPorConta implements FormaSaldo {

    private Conta conta;

    public SaldoPorConta(Conta conta) {
        this.conta = conta;
    }

    @Override
    public BigDecimal calcularSaldo() {
        List<Conta> contas = UsuarioUtil.getUsuarioLogado().getContas();
        if (contas.contains(conta)) {
            Conta conta = contas.get(contas.indexOf(this.conta));
            return MovimentacaoUtil.calculaSaldoMovimentacoes(conta.getMovimentacoes(), getRegraDataMovimentacao())
                    .add(MovimentacaoUtil.calculaSaldoMovimentacoesParceladas(conta.getMovimentacaoParceladas(), getRegraDataMovimentacaoParcelada(), getRegraDataMovimentacao()));
        }
        return null;
    }

    private Predicate<MovimentacaoParcelada> getRegraDataMovimentacaoParcelada() {
        return movimentacaoParcelada -> movimentacaoParcelada.getDataInicial().isBefore(LocalDate.now()) || movimentacaoParcelada.getDataInicial().isEqual(LocalDate.now());
    }

    private Predicate<Movimentacao> getRegraDataMovimentacao() {
        return movimentacao -> movimentacao.getData().isBefore(LocalDate.now()) || movimentacao.getData().isEqual(LocalDate.now());
    }
}
