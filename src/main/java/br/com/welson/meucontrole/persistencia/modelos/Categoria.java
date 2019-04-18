package br.com.welson.meucontrole.persistencia.modelos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "tbl_categorias")
@Getter
@Setter
public class Categoria extends Entidade {

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<Movimentacao> movimentacoes;

    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

}
