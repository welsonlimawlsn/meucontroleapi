package br.com.welson.meucontrole.persistencia.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_contas")
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public List<MovimentacaoParcelada> getMovimentacaoParceladas() {
        return movimentacaoParceladas;
    }

    public void setMovimentacaoParceladas(List<MovimentacaoParcelada> movimentacaoParceladas) {
        this.movimentacaoParceladas = movimentacaoParceladas;
    }
}
