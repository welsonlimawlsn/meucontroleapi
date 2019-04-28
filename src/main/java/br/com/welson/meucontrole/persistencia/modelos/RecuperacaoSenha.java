package br.com.welson.meucontrole.persistencia.modelos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_recuperacoes_senha")
@Getter
@Setter
public class RecuperacaoSenha extends Entidade {

    @Column(nullable = false)
    private String hash;

    @Column(nullable = false)
    private LocalDateTime validade;

    @Column(nullable = false)
    private Boolean usado;

    @ManyToOne(optional = false)
    private Usuario usuario;

    public RecuperacaoSenha() {
    }

    public RecuperacaoSenha(String hash, LocalDateTime validade, Boolean usado, Usuario usuario) {
        this.hash = hash;
        this.validade = validade;
        this.usado = usado;
        this.usuario = usuario;
    }

}
