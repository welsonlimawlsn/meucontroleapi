package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.dto.UsuarioDTO;
import br.com.welson.meucontrole.dto.UsuarioDTOResponse;
import br.com.welson.meucontrole.servicos.UsuarioService;
import br.com.welson.meucontrole.util.UsuarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static br.com.welson.meucontrole.util.URLBackEnd.comporURL;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class UsuarioEndpoint {

    private final UsuarioService usuarioService;

    public static final String PATH_USUARIO = "/usuario";
    private static final String NOVO_USUARIO = "/novo-usuario";

    @Autowired
    public UsuarioEndpoint(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Transactional
    @PostMapping(NOVO_USUARIO)
    public ResponseEntity<UsuarioDTOResponse> novo(@RequestBody UsuarioDTO usuario) {
        usuarioService.criar(usuario.convertToObject());
        return created(comporURL(PATH_USUARIO)).build();
    }

    @Transactional
    @GetMapping(value = PATH_USUARIO)
    public ResponseEntity<UsuarioDTOResponse> getUsuario() {
        return ok(UsuarioDTOResponse.convertToDTO(UsuarioUtil.getUsuarioLogado()));
    }
}
