package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.dto.MovimentacaoParceladaDTO;
import br.com.welson.meucontrole.dto.ReceitaDTO;
import br.com.welson.meucontrole.persistencia.modelos.Movimentacao;
import br.com.welson.meucontrole.persistencia.modelos.MovimentacaoParcelada;
import br.com.welson.meucontrole.servicos.MovimentacaoService;
import br.com.welson.meucontrole.util.InstanciaReceita;
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
public class ReceitaEndpoint {

    private final static String PATH_RECEITA = PATH_CONTA;
    private final static String PATH_RECEITA_CONTA = PATH_RECEITA + "/{idConta}";

    private final MovimentacaoService movimentacaoService;

    @Autowired
    public ReceitaEndpoint(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @Transactional
    @PostMapping(PATH_RECEITA_CONTA + "/receita")
    public ResponseEntity<Movimentacao> nova(@RequestBody ReceitaDTO receita, @PathVariable String idConta) {
        Movimentacao receitaCriada = movimentacaoService.criar(receita.convertToObject(), idConta);
        return created(comporURL(PATH_RECEITA, idConta, "receita", receitaCriada.getId())).build();
    }

    @Transactional
    @PostMapping(PATH_RECEITA_CONTA + "/receita-parcelada")
    public ResponseEntity<MovimentacaoParcelada> nova(@RequestBody MovimentacaoParceladaDTO movimentacaoParcelada, @PathVariable String idConta) {
        MovimentacaoParcelada receitaParceladaCriada = movimentacaoService.criar(movimentacaoParcelada.convertToObject(), idConta, new InstanciaReceita());
        return created(comporURL(PATH_RECEITA, idConta, "receita-parcelada", receitaParceladaCriada.getId())).build();
    }
}
