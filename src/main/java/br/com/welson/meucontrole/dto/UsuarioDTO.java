package br.com.welson.meucontrole.dto;

import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
