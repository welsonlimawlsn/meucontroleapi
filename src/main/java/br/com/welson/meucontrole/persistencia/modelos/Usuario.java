package br.com.welson.meucontrole.persistencia.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Entity
@Table(name = "tbl_usuarios")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Usuario implements IEntidade<String> {

    @Column(nullable = false, length = 20)
    private String nome;

    @Column(nullable = false, length = 20)
    private String sobrenome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 20)
    @Id
    @EqualsAndHashCode.Include
    private String usuario;

    @JsonProperty(access = WRITE_ONLY)
    @Column(nullable = false, length = 500)
    private String senha;

    @JsonProperty(access = READ_ONLY)
    @Column(nullable = false)
    private Boolean administrador;

    @JsonIgnore
    @Column(nullable = false)
    private Boolean ativa;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Conta> contas;

    public Usuario() {
    }

    public Usuario(Usuario usuario) {
        this(usuario.nome, usuario.sobrenome, usuario.email, usuario.usuario, usuario.senha, usuario.administrador, usuario.ativa, usuario.contas);
    }

    public Usuario(String nome, String sobrenome, String email, String usuario, String senha) {
        this(nome, sobrenome, email, usuario, senha, null, null, null);
    }

    @Override
    public String getIdentificador() {
        return usuario;
    }

    @Override
    public void setIdentificador(String id) {
        throw new UnsupportedOperationException("Não é possivel \"settar\" um identificador dessa forma");
    }
}
