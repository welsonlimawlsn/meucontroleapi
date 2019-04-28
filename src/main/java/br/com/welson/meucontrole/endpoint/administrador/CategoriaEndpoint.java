package br.com.welson.meucontrole.endpoint.administrador;

import br.com.welson.meucontrole.dto.CategoriaDTO;
import br.com.welson.meucontrole.persistencia.modelos.Categoria;
import br.com.welson.meucontrole.servicos.CategoriaService;
import br.com.welson.meucontrole.servicos.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

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
}
