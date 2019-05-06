package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.dto.DespesaDTO;
import br.com.welson.meucontrole.dto.MovimentacaoParceladaDTO;
import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;
import br.com.welson.meucontrole.servicos.MovimentacaoService;
import br.com.welson.meucontrole.util.InstanciaDespesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DespesaEndpoint {

    private final MovimentacaoService movimentacaoService;

    @Autowired
    public DespesaEndpoint(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @Transactional
    @PostMapping("usuario/conta/{idConta}/despesa")
    public ResponseEntity<Movimentacao> nova(@RequestBody DespesaDTO receita, @PathVariable String idConta) {
        return new ResponseEntity<>(movimentacaoService.criar(receita.convertToObject(), idConta), HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("usuario/conta/{idConta}/despesa-parcelada")
    public ResponseEntity<MovimentacaoParcelada> nova(@RequestBody MovimentacaoParceladaDTO movimentacaoParcelada, @PathVariable String idConta) {
        return new ResponseEntity<>(movimentacaoService.criar(movimentacaoParcelada.convertToObject(), idConta, new InstanciaDespesa()), HttpStatus.CREATED);
    }
}
