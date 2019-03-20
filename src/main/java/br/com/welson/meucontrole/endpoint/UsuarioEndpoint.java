package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.dto.UsuarioDTO;
import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import br.com.welson.meucontrole.servicos.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioEndpoint {

    private final CrudService<Usuario> usuarioCrudService;

    @Autowired
    public UsuarioEndpoint(CrudService<Usuario> usuarioCrudService) {
        this.usuarioCrudService = usuarioCrudService;
    }

    @Transactional
    @PostMapping("/novo-usuario")
    public ResponseEntity<Usuario> novo(@RequestBody UsuarioDTO usuario) {
        return new ResponseEntity<>(usuarioCrudService.criar(usuario.convertToObject()), HttpStatus.CREATED);
    }
}
