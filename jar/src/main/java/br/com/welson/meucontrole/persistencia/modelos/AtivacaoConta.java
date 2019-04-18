package br.com.welson.meucontrole.persistencia.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_ativacoes_contas")
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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
