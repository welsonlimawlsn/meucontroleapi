package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.dto.DespesaDTO;
import br.com.welson.meucontrole.dto.MovimentacaoParceladaDTO;
import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;
import br.com.welson.meucontrole.servicos.MovimentacaoService;
import br.com.welson.meucontrole.util.InstanciaDespesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static br.com.welson.meucontrole.endpoint.ContaEndpoint.PATH_CONTA;
import static br.com.welson.meucontrole.util.URLBackEnd.comporURL;
import static org.springframework.http.ResponseEntity.created;

@RestController
public class DespesaEndpoint {

    private final MovimentacaoService movimentacaoService;

    private final static String PATH_DESPESA = PATH_CONTA;
    private final static String PATH_DESPESA_CONTA = PATH_DESPESA + "/{idConta}";

    @Autowired
    public DespesaEndpoint(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @Transactional
    @PostMapping(PATH_DESPESA_CONTA + "/despesa")
    public ResponseEntity<Movimentacao> nova(@RequestBody DespesaDTO despesa, @PathVariable String idConta) {
        Movimentacao movimentacaoCriada = movimentacaoService.criar(despesa.convertToObject(), idConta);
        return created(comporURL(PATH_DESPESA, idConta, "despesa", movimentacaoCriada.getId())).build();
    }

    @Transactional
    @PostMapping(PATH_DESPESA_CONTA + "/despesa-parcelada")
    public ResponseEntity<MovimentacaoParcelada> nova(@RequestBody MovimentacaoParceladaDTO movimentacaoParcelada, @PathVariable String idConta) {
        MovimentacaoParcelada movimentacaoParceladaCriada = movimentacaoService.criar(movimentacaoParcelada.convertToObject(), idConta, new InstanciaDespesa());
        return created(comporURL(PATH_DESPESA, idConta, "despesa-parcelada", movimentacaoParceladaCriada.getId())).build();
    }
}
