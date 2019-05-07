package br.com.welson.meucontrole.persistencia.modelos;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class RecuperacaoSenha implements IEntidade<String> {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(nullable = false, unique = true)
    private String hash;

    @Column(nullable = false)
    private LocalDateTime validade;

    @Column(nullable = false)
    private Boolean usado;

    @ManyToOne(optional = false)
    private Usuario usuario;

    public RecuperacaoSenha() {
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
