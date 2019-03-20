package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.dto.MovimentacaoParceladaDTO;
import br.com.welson.meucontrole.dto.ReceitaDTO;
import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;
import br.com.welson.meucontrole.servicos.MovimentacaoService;
import br.com.welson.meucontrole.util.InstanciaReceita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceitaEndpoint {

    private final MovimentacaoService movimentacaoService;

    @Autowired
    public ReceitaEndpoint(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @Transactional
    @PostMapping("usuario/conta/{idConta}/receita")
    public ResponseEntity<Movimentacao> nova(@RequestBody ReceitaDTO receita, @PathVariable Long idConta) {
        return new ResponseEntity<>(movimentacaoService.criar(receita.convertToObject(), idConta), HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("usuario/conta/{idConta}/receita-parcelada")
    public ResponseEntity<MovimentacaoParcelada> nova(@RequestBody MovimentacaoParceladaDTO movimentacaoParcelada, @PathVariable Long idConta) {
        return new ResponseEntity<>(movimentacaoService.criar(movimentacaoParcelada.convertToObject(), idConta, new InstanciaReceita()), HttpStatus.CREATED);
    }
}
