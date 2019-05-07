package br.com.welson.meucontrole.endpoint.administrador;

import br.com.welson.meucontrole.dto.CategoriaDTO;
import br.com.welson.meucontrole.persistencia.modelos.Categoria;
import br.com.welson.meucontrole.servicos.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class CategoriaEndpoint {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaEndpoint(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("administrador/categoria")
    @Transactional
    public ResponseEntity<Categoria> nova(@RequestBody CategoriaDTO categoria) {
        return new ResponseEntity<>(categoriaService.criar(categoria.convertToObject()), CREATED);
    }

    @GetMapping("administrador/categoria")
    @Transactional
    public ResponseEntity<Iterable<Categoria>> listAll() {
        return new ResponseEntity<>(categoriaService.listarTodos(), OK);
    }
}
