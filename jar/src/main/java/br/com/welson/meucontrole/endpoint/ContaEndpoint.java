package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.dto.ContaDTO;
import br.com.welson.meucontrole.persistencia.modelos.Conta;
import br.com.welson.meucontrole.servicos.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContaEndpoint {

    private final ContaService contaService;

    @Autowired
    public ContaEndpoint(ContaService contaService) {
        this.contaService = contaService;
    }

    @Transactional
    @GetMapping("usuario/conta")
    public ResponseEntity<Iterable<Conta>> listar() {
        return new ResponseEntity<>(contaService.listarTodos(), HttpStatus.OK);
    }

    @Transactional
    @GetMapping("usuario/conta/{id}")
    public ResponseEntity<Conta> getPorId(@PathVariable Long id) {
        return new ResponseEntity<>(contaService.procurarPeloId(id), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("usuario/conta")
    public ResponseEntity<Conta> nova(@RequestBody ContaDTO conta) {
        return new ResponseEntity<>(contaService.criar(conta.convertToObject()), HttpStatus.OK);
    }
}
