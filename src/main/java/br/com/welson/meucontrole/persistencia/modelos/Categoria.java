package br.com.welson.meucontrole.persistencia.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Categoria implements IEntidade<String> {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<Movimentacao> movimentacoes;

    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    @Override
    public String getIdentificador() {
        return id;
    }

    @Override
    public void setIdentificador(String id) {
        this.id = id;
    }
}
