package br.com.welson.meucontrole.persistencia.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_contas")
@Getter
@Setter
public class Conta extends Entidade {

    @JsonIgnore
    @ManyToOne(optional = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "conta")
    private List<Movimentacao> movimentacoes;

    @JsonIgnore
    @OneToMany(mappedBy = "conta")
    private List<MovimentacaoParcelada> movimentacaoParceladas;

    public Conta() {
    }

    public Conta(Usuario usuario, String nome) {
        this.usuario = usuario;
        this.nome = nome;
    }

    public Conta(String nome) {
        this.nome = nome;
    }

    public Conta(Long id) {
        this.id = id;
    }

}
