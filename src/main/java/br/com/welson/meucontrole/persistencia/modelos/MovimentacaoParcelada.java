package br.com.welson.meucontrole.persistencia.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_movimentacoes_parceladas")
@Getter
@Setter
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
}
