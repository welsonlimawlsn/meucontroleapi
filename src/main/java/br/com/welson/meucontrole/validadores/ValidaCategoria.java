package br.com.welson.meucontrole.validadores;

import br.com.welson.meucontrole.excecoes.BadRequestException;
import br.com.welson.meucontrole.persistencia.modelos.Categoria;

import static br.com.welson.meucontrole.util.ErrorMessages.NOME_CATEGORIA_OBRIGATORIO;

public class ValidaCategoria implements Validador<Categoria> {

    private Categoria categoria;

    public ValidaCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public void validar() {
        if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
            throw new BadRequestException(NOME_CATEGORIA_OBRIGATORIO);
        }
    }

    @Override
    public Categoria getEntidade() {
        return categoria;
    }
}
