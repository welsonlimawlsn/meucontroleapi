package br.com.welson.meucontrole.servicos.implemetacoes;

import br.com.welson.meucontrole.excecoes.BadRequestException;
import br.com.welson.meucontrole.persistencia.modelos.Conta;
import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import br.com.welson.meucontrole.persistencia.repositorios.UsuarioRepositorio;
import br.com.welson.meucontrole.servicos.AtivacaoContaService;
import br.com.welson.meucontrole.servicos.CrudService;
import br.com.welson.meucontrole.servicos.UsuarioService;
import br.com.welson.meucontrole.validadores.ValidaCamposEntidade;
import br.com.welson.meucontrole.validadores.ValidaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepositorio usuarioRepositorio;
    private final AtivacaoContaService ativacaoContaService;
    private final CrudService<Conta> contaCrudService;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepositorio usuarioRepositorio, AtivacaoContaService ativacaoContaService,
                              CrudService<Conta> contaCrudService) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.ativacaoContaService = ativacaoContaService;
        this.contaCrudService = contaCrudService;
    }

    @Override
    public Usuario procurarPeloId(Long id) {
        return null;
    }

    @Override
    public Usuario criar(Usuario entidade) {
        ValidaCamposEntidade.validar(new ValidaUsuario(entidade, true, usuarioRepositorio), true);
        setValoresPadroes(entidade);
        Usuario usuarioCriado = usuarioRepositorio.save(entidade);
        contaCrudService.criar(new Conta(usuarioCriado, "Carteira"));
        ativacaoContaService.criar(usuarioCriado);
        return usuarioCriado;
    }

    private void setValoresPadroes(Usuario entidade) {
        entidade.setAtiva(false);
        entidade.setAdministrador(false);
        entidade.setSenha(new BCryptPasswordEncoder().encode(entidade.getSenha()));
    }

    @Override
    public Usuario alterar(Usuario entidade) {
        verificarSeUsuarioExiste(entidade);
        ValidaCamposEntidade.validar(new ValidaUsuario(entidade), false);
        return usuarioRepositorio.save(entidade);
    }

    private void verificarSeUsuarioExiste(Usuario entidade) {
        if (entidade.getId() != null) {
            usuarioRepositorio.findById(entidade.getId()).orElseThrow(() -> new BadRequestException("Esse usuario não existe."));
        }
    }

    @Override
    public Usuario getUsuarioPorEmail(String email) {
        return usuarioRepositorio.findByEmail(email).orElse(null);
    }

    @Override
    public void apagar(Usuario entidade) {

    }

    @Override
    public Iterable<Usuario> listarTodos() {
        return null;
    }

    @Override
    public void ativarConta(String hash) {
        Usuario usuario = ativacaoContaService.pegarAtivacaoConta(hash).getUsuario();
        usuario.setAtiva(true);
        alterar(usuario);
    }
}
