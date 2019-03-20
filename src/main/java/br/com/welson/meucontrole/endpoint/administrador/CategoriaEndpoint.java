package br.com.welson.meucontrole.endpoint.administrador;

import br.com.welson.meucontrole.dto.CategoriaDTO;
import br.com.welson.meucontrole.persistencia.modelos.Categoria;
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

    private final CrudService<Categoria> categoriaCrudService;

    @Autowired
    public CategoriaEndpoint(CrudService<Categoria> categoriaCrudService) {
        this.categoriaCrudService = categoriaCrudService;
    }

    @PostMapping("administrador/categoria")
    @Transactional
    public ResponseEntity<Categoria> nova(@RequestBody CategoriaDTO categoria) {
        return new ResponseEntity<>(categoriaCrudService.criar(categoria.convertToObject()), CREATED);
    }
}
