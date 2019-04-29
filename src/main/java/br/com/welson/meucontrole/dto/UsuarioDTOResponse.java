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
public class UsuarioDTOResponse {

    private String nome;
    private String sobrenome;
    private String email;
    private String usuario;
    private Boolean administrador;

    public static UsuarioDTOResponse convertToDTO(Usuario usuario) {
        return new UsuarioDTOResponse(usuario.getNome(), usuario.getSobrenome(), usuario.getEmail(), usuario.getUsuario(), usuario.getAdministrador());
    }
}
