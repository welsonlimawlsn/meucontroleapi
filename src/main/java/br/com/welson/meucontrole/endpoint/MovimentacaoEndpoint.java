package br.com.welson.meucontrole.endpoint;

import br.com.welson.meucontrole.dto.MovimentacaoDTOResponse;
import br.com.welson.meucontrole.servicos.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.Collectors;

@RestController
public class MovimentacaoEndpoint {

    private MovimentacaoService movimentacaoService;

    @Autowired
    public MovimentacaoEndpoint(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @GetMapping("usuario/movimentacoes/mes-atual")
    public ResponseEntity<Iterable<MovimentacaoDTOResponse>> getMovimentacoesMesAtual() {
        return new ResponseEntity<>(
                movimentacaoService.getMovimentacoesEntre(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()), LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()))
                        .stream().map(MovimentacaoDTOResponse::convertToDTO).collect(Collectors.toList()), HttpStatus.OK);
    }
}
