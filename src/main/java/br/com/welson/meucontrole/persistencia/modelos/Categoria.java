package br.com.welson.meucontrole.persistencia.modelos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "tbl_categorias")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria implements IEntidade<Long> {

    @Id
    @GeneratedValue(strategy = AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<Movimentacao> movimentacoes;

    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    @Override
    public Long getIdentificador() {
        return id;
    }
}
