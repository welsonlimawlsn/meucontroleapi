package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.dto.UsuarioDTO;
import br.com.welson.meucontrole.dto.UsuarioDTOResponse;
import br.com.welson.meucontrole.servicos.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioEndpoint {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioEndpoint(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Transactional
    @PostMapping("/novo-usuario")
    public ResponseEntity<UsuarioDTOResponse> novo(@RequestBody UsuarioDTO usuario) {
        return new ResponseEntity<>(UsuarioDTOResponse.convertToDTO(usuarioService.criar(usuario.convertToObject())), HttpStatus.CREATED);
    }
}
