package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.persistencia.modelos.Conta;
import br.com.welson.meucontrole.persistencia.modelos.Saldo;
import br.com.welson.meucontrole.saldos.SaldoPorConta;
import br.com.welson.meucontrole.servicos.SaldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class SaldoEndpoint {

    private final SaldoService saldoService;

    @Autowired
    public SaldoEndpoint(SaldoService saldoService) {
        this.saldoService = saldoService;
    }

    @GetMapping("usuario/conta/{idConta}/saldo")
    @Transactional
    public ResponseEntity<Saldo> saldoMesAtualContaEspecifica(@PathVariable Long idConta, @RequestParam(value = "fimMes", required = false) boolean fimDeMes) {
        return new ResponseEntity<>(saldoService.getSaldo(new SaldoPorConta(new Conta(idConta), fimDeMes)), OK);
    }

    @GetMapping("usuario/conta/{idConta}/saldo/{dataLimite}")
    @Transactional
    public ResponseEntity<Saldo> saldoDataLimiteContaEspecifica(@PathVariable Long idConta, @PathVariable LocalDate dataLimite) {
        return new ResponseEntity<>(saldoService.getSaldo(new SaldoPorConta(new Conta(idConta), dataLimite)), OK);
    }

    @GetMapping("usuario/saldo")
    @Transactional
    public ResponseEntity<String> saldoMesAtualGeral() {
        return new ResponseEntity<>("", OK);
    }

    @GetMapping("usuario/saldo/{dataLimite}")
    @Transactional
    public ResponseEntity<String> saldoMesAtualGeral(@PathVariable LocalDate dataLimite) {
        return new ResponseEntity<>("", OK);
    }
}
