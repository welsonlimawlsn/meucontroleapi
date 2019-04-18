package br.com.welson.meucontrole.dto;

import br.com.welson.meucontrole.persistencia.modelos.Categoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDTO implements DTO<Categoria> {

    private String nome;

    @Override
    public Categoria convertToObject() {
        return new Categoria(nome);
    }
}
