package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.excecoes.NotFoundException;
import br.com.welson.meucontrole.persistencia.modelos.NovaSenha;
import br.com.welson.meucontrole.servicos.RecuperacaoSenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.welson.meucontrole.util.ErrorMessages.PAGINA_NAO_EXISTE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class RecuperacaoSenhaEndpoint {

    private final RecuperacaoSenhaService recuperacaoSenhaService;

    @Autowired
    public RecuperacaoSenhaEndpoint(RecuperacaoSenhaService recuperacaoSenhaService) {
        this.recuperacaoSenhaService = recuperacaoSenhaService;
    }

    @GetMapping("/recuperacao-senha/pedir/{email}")
    public ResponseEntity recuperar(@PathVariable String email) {
        recuperacaoSenhaService.criar(email);
        return ok().build();
    }

    @PostMapping("/recuperacao-senha/nova-senha/{hash}")
    public ResponseEntity recuperar(@RequestBody NovaSenha novaSenha, @PathVariable String hash) {
        if (recuperacaoSenhaService.recuperarSenha(hash, novaSenha))
            return ok().build();
        throw new NotFoundException(PAGINA_NAO_EXISTE);
    }

    @GetMapping("/recuperacao-senha/validar/{hash}")
    public ResponseEntity validar(@PathVariable String hash) {
        if (recuperacaoSenhaService.getRecuperacaoSenhaSeExistir(hash) != null)
            return ok().build();
        return new ResponseEntity<>(NOT_FOUND);
    }
}
