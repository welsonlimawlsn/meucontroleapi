package br.com.welson.meucontrole.persistencia.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public abstract class Movimentacao extends Entidade {

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, scale = 2, precision = 10)
    protected BigDecimal valor;

    @Column(nullable = false)
    private LocalDate data;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Conta conta;

    @ManyToOne(optional = false)
    private Categoria categoria;

    @Column(nullable = false)
    private Boolean consolidada;

    @ManyToOne
    @JsonIgnore
    private MovimentacaoParcelada movimentacaoParcelada;

    public Movimentacao() {
    }

    public Movimentacao(String descricao, BigDecimal valor, LocalDate data, Conta conta, Categoria categoria, Boolean consolidada, MovimentacaoParcelada movimentacaoParcelada) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.conta = conta;
        this.categoria = categoria;
        this.consolidada = consolidada;
        this.movimentacaoParcelada = movimentacaoParcelada;
        mudarSinalCasoNecessario();
    }

    public Movimentacao(String descricao, BigDecimal valor, LocalDate data, Conta conta, Categoria categoria, Boolean consolidada) {
        this(descricao, valor, data, conta, categoria, consolidada, null);
    }

    public Movimentacao(String descricao, BigDecimal valor, Conta conta, Categoria categoria, Boolean consolidada) {
        this(descricao, valor, LocalDate.now(), conta, categoria, consolidada, null);
    }

    public Movimentacao(String descricao, BigDecimal valor, LocalDate data, Categoria categoria, Boolean consolidada) {
        this(descricao, valor, data, null, categoria, consolidada, null);
    }

    public abstract void mudarSinalCasoNecessario();

    void mudarSinal() {
        valor = valor.negate();
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
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

    public MovimentacaoParcelada getMovimentacaoParcelada() {
        return movimentacaoParcelada;
    }

    public void setMovimentacaoParcelada(MovimentacaoParcelada movimentacaoParcelada) {
        this.movimentacaoParcelada = movimentacaoParcelada;
    }

    public Boolean getConsolidada() {
        return consolidada;
    }

    public void setConsolidada(Boolean consolidada) {
        this.consolidada = consolidada;
    }
}
