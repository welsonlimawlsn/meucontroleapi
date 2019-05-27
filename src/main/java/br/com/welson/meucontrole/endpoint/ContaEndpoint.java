package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.dto.ContaDTO;
import br.com.welson.meucontrole.persistencia.modelos.Conta;
import br.com.welson.meucontrole.servicos.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static br.com.welson.meucontrole.endpoint.UsuarioEndpoint.PATH_USUARIO;
import static br.com.welson.meucontrole.util.URLBackEnd.comporURL;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class ContaEndpoint {

    private final ContaService contaService;

    public final static String PATH_CONTA = PATH_USUARIO + "/conta";

    @Autowired
    public ContaEndpoint(ContaService contaService) {
        this.contaService = contaService;
    }

    @Transactional
    @GetMapping(PATH_CONTA)
    public ResponseEntity<Iterable<Conta>> listar() {
        return ok(contaService.listarTodos());
    }

    @Transactional
    @GetMapping(PATH_CONTA + "/{id}")
    public ResponseEntity<Conta> getPorId(@PathVariable String id) {
        return ok(contaService.procurarPeloId(id));
    }

    @Transactional
    @PostMapping(PATH_CONTA)
    public ResponseEntity<Conta> nova(@RequestBody ContaDTO conta) {
        Conta contaCriada = contaService.criar(conta.convertToObject());
        return created(comporURL(PATH_CONTA, contaCriada.getId())).build();
    }
}
