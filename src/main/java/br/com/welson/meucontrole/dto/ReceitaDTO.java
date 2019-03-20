package br.com.welson.meucontrole.dto;

import br.com.welson.meucontrole.persistencia.modelos.Categoria;
import br.com.welson.meucontrole.persistencia.modelos.Receita;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReceitaDTO implements DTO<Receita> {

    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private Categoria categoria;
    private Boolean consolidada;

    @Override
    public Receita convertToObject() {
        return new Receita(descricao, valor, data, categoria, consolidada);
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Boolean getConsolidada() {
        return consolidada;
    }

    public void setConsolidada(Boolean consolidada) {
        this.consolidada = consolidada;
    }
}
