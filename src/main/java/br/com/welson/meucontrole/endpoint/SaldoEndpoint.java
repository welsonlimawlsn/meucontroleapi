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

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class SaldoEndpoint {

    private final SaldoService saldoService;

    @Autowired
    public SaldoEndpoint(SaldoService saldoService) {
        this.saldoService = saldoService;
    }

    @GetMapping("usuario/conta/{idConta}/saldo")
    @Transactional
    public ResponseEntity<Saldo> saldoMesAtualContaEspecifica(@PathVariable String idConta, @RequestParam(value = "fimMes", required = false) boolean fimDeMes) {
        return ok(saldoService.getSaldo(new SaldoPorConta(new Conta(idConta), fimDeMes)));
    }

    @GetMapping("usuario/conta/{idConta}/saldo/{dataLimite}")
    @Transactional
    public ResponseEntity<Saldo> saldoDataLimiteContaEspecifica(@PathVariable String idConta, @PathVariable LocalDate dataLimite) {
        return ok(saldoService.getSaldo(new SaldoPorConta(new Conta(idConta), dataLimite)));
    }

    @GetMapping("usuario/saldo")
    @Transactional
    public ResponseEntity<String> saldoMesAtualGeral() {
        return ok("");
    }

    @GetMapping("usuario/saldo/{dataLimite}")
    @Transactional
    public ResponseEntity<String> saldoMesAtualGeral(@PathVariable LocalDate dataLimite) {
        return ok("");
    }
}
