package br.com.welson.meucontrole.persistencia.modelos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_ativacoes_contas")
@Getter
@Setter
public class AtivacaoConta extends Entidade {

    @Column(nullable = false)
    private String hash;

    @OneToOne(optional = false)
    private Usuario usuario;

    public AtivacaoConta() {
    }

    public AtivacaoConta(String hash, Usuario usuario) {
        this.hash = hash;
        this.usuario = usuario;
    }

}
