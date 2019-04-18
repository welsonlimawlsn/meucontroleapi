package br.com.welson.meucontrole.dto;

import br.com.welson.meucontrole.persistencia.modelos.Usuario;

public class UsuarioDTO implements DTO<Usuario> {

    private String nome;
    private String sobrenome;
    private String email;
    private String usuario;
    private String senha;

    @Override
    public Usuario convertToObject() {
        return new Usuario(nome, sobrenome, email, usuario, senha);
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
}
