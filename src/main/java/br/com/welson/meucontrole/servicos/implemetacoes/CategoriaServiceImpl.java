package br.com.welson.meucontrole.servicos.implemetacoes;

import br.com.welson.meucontrole.excecoes.NotFoundException;
import br.com.welson.meucontrole.persistencia.modelos.Categoria;
import br.com.welson.meucontrole.persistencia.repositorios.CategoriaRepositorio;
import br.com.welson.meucontrole.servicos.CategoriaService;
import br.com.welson.meucontrole.validadores.ValidaCamposEntidade;
import br.com.welson.meucontrole.validadores.ValidaCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.welson.meucontrole.util.ErrorMessages.CATEGORIA_NAO_EXISTE;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepositorio categoriaRepositorio;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepositorio categoriaRepositorio) {
        this.categoriaRepositorio = categoriaRepositorio;
    }

    @Override
    public Categoria procurarPeloId(String id) {
        if (id != null) {
            return categoriaRepositorio.findById(id).orElseThrow(() -> new NotFoundException(CATEGORIA_NAO_EXISTE));
        }
        return null;
    }

    @Override
    public Categoria criar(Categoria entidade) {
        ValidaCamposEntidade.validar(new ValidaCategoria(entidade), true);
        return categoriaRepositorio.save(entidade);
    }

    @Override
    public Categoria alterar(Categoria entidade) {
        return null;
    }

    @Override
    public void apagar(Categoria entidade) {

    }

    @Override
    public Iterable<Categoria> listarTodos() {
        return categoriaRepositorio.findAll();
    }
}
