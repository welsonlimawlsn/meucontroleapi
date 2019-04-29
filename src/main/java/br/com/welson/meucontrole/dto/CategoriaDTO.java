package br.com.welson.meucontrole.dto;

import br.com.welson.meucontrole.persistencia.modelos.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO implements DTO<Categoria> {

    private String nome;

    @Override
    public Categoria convertToObject() {
        return new Categoria(nome);
    }

    public static CategoriaDTO convertToDTO(Categoria categoria) {
        return new CategoriaDTO(categoria.getNome());
    }
}
