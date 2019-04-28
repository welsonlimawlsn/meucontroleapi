package br.com.welson.meucontrole.validadores;

import br.com.welson.meucontrole.excecoes.BadRequestException;
import br.com.welson.meucontrole.persistencia.modelos.Entidade;
import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;

public class ValidaMovimentacaoParcelada implements Validador {

    private MovimentacaoParcelada movimentacaoParcelada;

    public ValidaMovimentacaoParcelada(MovimentacaoParcelada movimentacaoParcelada) {
        this.movimentacaoParcelada = movimentacaoParcelada;
    }

    @Override
    public void validar() {
        if (movimentacaoParcelada.getValor() == null || movimentacaoParcelada.getValor().doubleValue() == 0) {
            throw new BadRequestException("O valor da movimentação precisa ser diferente de 0.");
        }
        if (movimentacaoParcelada.getConta() == null) {
            throw new BadRequestException("A movimentação precisa está associada a uma conta.");
        }
        if (movimentacaoParcelada.getDescricao() == null || movimentacaoParcelada.getDescricao().isEmpty()) {
            throw new BadRequestException("A movimentação precisa ter uma descrição.");
        }
        if (movimentacaoParcelada.getDataInicial() == null) {
            throw new BadRequestException("A movimentação precisa ter uma data inicial.");
        }
        if (movimentacaoParcelada.getQuantidadeParcelas() == null || movimentacaoParcelada.getQuantidadeParcelas() <= 1) {
            throw new BadRequestException("A movimentação precisa ter mais de uma parcela");
        }
    }

    @Override
    public Entidade getEntidade() {
        return movimentacaoParcelada;
    }
}
