package br.com.welson.meucontrole.validadores;

import br.com.welson.meucontrole.excecoes.BadRequestException;
import br.com.welson.meucontrole.persistencia.modelos.Categoria;
import br.com.welson.meucontrole.persistencia.modelos.Entidade;

public class ValidaCategoria implements Validador {

    private Categoria categoria;

    public ValidaCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public void validar() {
        if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
            throw new BadRequestException("Você deve dar um nome a categoria!");
        }
    }

    @Override
    public Entidade getEntidade() {
        return categoria;
    }
}
