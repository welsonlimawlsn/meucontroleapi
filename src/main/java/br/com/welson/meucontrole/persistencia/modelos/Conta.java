package br.com.welson.meucontrole.persistencia.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_contas")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Conta implements IEntidade<String> {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "conta", fetch = FetchType.EAGER)
    private List<Movimentacao> movimentacoes;

    @JsonIgnore
    @OneToMany(mappedBy = "conta", fetch = FetchType.EAGER)
    private List<MovimentacaoParcelada> movimentacaoParceladas;

    public Conta() {
    }

    public Conta(Usuario usuario, String nome) {
        this(null, usuario, nome, null, null);
    }

    public Conta(String nome) {
        this.nome = nome;
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
