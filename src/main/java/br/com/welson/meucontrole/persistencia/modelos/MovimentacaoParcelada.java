package br.com.welson.meucontrole.persistencia.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_movimentacoes_parceladas")
public class MovimentacaoParcelada extends Entidade {

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, scale = 2, precision = 10)
    protected BigDecimal valor;

    @Column(nullable = false)
    private LocalDate dataInicial;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Conta conta;

    @ManyToOne(optional = false)
    private Categoria categoria;

    @Column(nullable = false)
    private Integer quantidadeParcelas;

    @OneToMany(mappedBy = "movimentacaoParcelada")
    private List<Movimentacao> movimentacoes;

    public MovimentacaoParcelada() {
    }

    public MovimentacaoParcelada(String descricao, BigDecimal valor, LocalDate dataInicial, Categoria categoria, Integer quantidadeParcelas, List<Movimentacao> movimentacoes) {
        this.descricao = descricao;
        this.valor = valor;
        this.dataInicial = dataInicial;
        this.categoria = categoria;
        this.quantidadeParcelas = quantidadeParcelas;
        this.movimentacoes = movimentacoes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(Integer quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }
}
