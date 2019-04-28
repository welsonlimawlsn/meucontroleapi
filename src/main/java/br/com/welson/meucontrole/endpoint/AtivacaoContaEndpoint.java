package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.servicos.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtivacaoContaEndpoint {

    private final UsuarioService usuarioService;

    @Autowired
    public AtivacaoContaEndpoint(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Transactional
    @GetMapping("/ativacao-conta/{hash}")
    public ResponseEntity ativarConta(@PathVariable("hash") String hash) {
        usuarioService.ativarConta(hash);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
