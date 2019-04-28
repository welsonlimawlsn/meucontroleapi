package br.com.welson.meucontrole.validadores;

import br.com.welson.meucontrole.excecoes.BadRequestException;
import br.com.welson.meucontrole.persistencia.modelos.Categoria;
import br.com.welson.meucontrole.persistencia.modelos.Entidade;
import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.servicos.CrudService;

import java.time.LocalDate;

public class ValidaMovimentacao implements Validador {

    private Movimentacao movimentacao;
    private CrudService<Categoria> categoriaCrudService;

    public ValidaMovimentacao(Movimentacao movimentacao, CrudService<Categoria> categoriaCrudService) {
        this.movimentacao = movimentacao;
        this.categoriaCrudService = categoriaCrudService;
    }

    @Override
    public void validar() {
        if (movimentacao.getValor() == null || movimentacao.getValor().doubleValue() == 0) {
            throw new BadRequestException("O valor da movimentação precisa ser diferente de 0.");
        }
        if (movimentacao.getConta() == null) {
            throw new BadRequestException("A movimentação precisa está associada a uma conta.");
        }
        if (movimentacao.getDescricao() == null || movimentacao.getDescricao().isEmpty()) {
            throw new BadRequestException("A movimentação precisa ter uma descrição.");
        }
        if (movimentacao.getData() == null) {
            movimentacao.setData(LocalDate.now());
        }
        if (movimentacao.getConsolidada() == null) {
            movimentacao.setConsolidada(false);
        }
        if (movimentacao.getCategoria() == null || categoriaCrudService.procurarPeloId(movimentacao.getCategoria().getId()) == null) {
            throw new BadRequestException("A movimentação precisa ter uma categoria.");
        }
        movimentacao.mudarSinalCasoNecessario();
    }

    @Override
    public Entidade getEntidade() {
        return movimentacao;
    }
}
