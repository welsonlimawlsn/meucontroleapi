package br.com.welson.meucontrole.persistencia.modelos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_recuperacoes_senha")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RecuperacaoSenha implements IEntidade<String> {

    @Column(nullable = false)
    @Id
    @EqualsAndHashCode.Include
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

    @Override
    public String getIdentificador() {
        return hash;
    }

    @Override
    public void setIdentificador(String id) {
        throw new UnsupportedOperationException("Não é possivel \"settar\" um identificador dessa forma");
    }
}
