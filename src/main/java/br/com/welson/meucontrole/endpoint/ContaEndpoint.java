package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.dto.ContaDTO;
import br.com.welson.meucontrole.persistencia.modelos.Conta;
import br.com.welson.meucontrole.servicos.ContaService;
import br.com.welson.meucontrole.util.ConstantesDeConfiguracao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static br.com.welson.meucontrole.endpoint.UsuarioEndpoint.PATH_USUARIO;

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
        return new ResponseEntity<>(contaService.listarTodos(), HttpStatus.OK);
    }

    @Transactional
    @GetMapping(PATH_CONTA + "/{id}")
    public ResponseEntity<Conta> getPorId(@PathVariable String id) {
        return new ResponseEntity<>(contaService.procurarPeloId(id), HttpStatus.OK);
    }

    @Transactional
    @PostMapping(PATH_CONTA)
    public ResponseEntity<Conta> nova(@RequestBody ContaDTO conta) {
        Conta contaCriada = contaService.criar(conta.convertToObject());
        return ResponseEntity.created(URI.create(ConstantesDeConfiguracao.ENDERECO_API + PATH_CONTA + "/" + contaCriada.getId())).build();
    }
}
