package br.com.welson.meucontrole.persistencia.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Entity
@Table(name = "tbl_usuarios")
public class Usuario extends Entidade {

    @Column(nullable = false, length = 20)
    private String nome;

    @Column(nullable = false, length = 20)
    private String sobrenome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 20)
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
    @OneToMany(mappedBy = "usuario")
    private List<Conta> contas;

    public Usuario() {
    }

    public Usuario(Usuario usuario) {
        this(usuario.id, usuario.nome, usuario.sobrenome, usuario.email, usuario.usuario, usuario.senha, usuario.administrador, usuario.ativa, usuario.contas);
    }

    public Usuario(String nome, String sobrenome, String email, String usuario, String senha) {
        this(null, nome, sobrenome, email, usuario, senha, null, null, null);
    }

    public Usuario(Long id, String nome, String sobrenome, String email, String usuario, String senha, Boolean administrador, Boolean ativa, List<Conta> contas) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.usuario = usuario;
        this.senha = senha;
        this.administrador = administrador;
        this.ativa = ativa;
        this.contas = contas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    public Boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

}
