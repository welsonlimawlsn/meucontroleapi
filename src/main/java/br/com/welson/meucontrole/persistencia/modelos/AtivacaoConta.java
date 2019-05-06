package br.com.welson.meucontrole.persistencia.modelos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_ativacoes_contas")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AtivacaoConta implements IEntidade<String> {

    @Id
    @EqualsAndHashCode.Include
    private String hash;

    @OneToOne(optional = false)
    private Usuario usuario;

    public AtivacaoConta() {
    }

    public AtivacaoConta(String hash, Usuario usuario) {
        this.hash = hash;
        this.usuario = usuario;
    }

    @Override
    public String getIdentificador() {
        return hash;
    }

    @Override
    public void setIdentificador(String id) {
        throw new UnsupportedOperationException("Não é possivel \"settar\" um identificador dessa forma");
    }
}
