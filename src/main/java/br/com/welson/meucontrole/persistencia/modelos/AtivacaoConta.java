package br.com.welson.meucontrole.persistencia.modelos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_ativacoes_contas")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class AtivacaoConta implements IEntidade<String> {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(unique = true, nullable = false)
    private String hash;

    @OneToOne(optional = false)
    private Usuario usuario;

    public AtivacaoConta() {
    }

    @Override
    public String getIdentificador() {
        return id;
    }

    @Override
    public void setIdentificador(String id) {
        this.id = id;
    }
}
