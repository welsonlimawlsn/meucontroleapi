package br.com.welson.meucontrole.servicos.implemetacoes;

import br.com.welson.meucontrole.excecoes.BadRequestException;
import br.com.welson.meucontrole.persistencia.modelos.Conta;
import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import br.com.welson.meucontrole.persistencia.repositorios.UsuarioRepositorio;
import br.com.welson.meucontrole.servicos.AtivacaoContaService;
import br.com.welson.meucontrole.servicos.ContaService;
import br.com.welson.meucontrole.servicos.UsuarioService;
import br.com.welson.meucontrole.validadores.ValidaCamposEntidade;
import br.com.welson.meucontrole.validadores.ValidaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.welson.meucontrole.util.ErrorMessages.USUARIO_NAO_EXISTE;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepositorio usuarioRepositorio;
    private final AtivacaoContaService ativacaoContaService;
    private final ContaService contaService;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepositorio usuarioRepositorio, AtivacaoContaService ativacaoContaService,
                              ContaService contaService) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.ativacaoContaService = ativacaoContaService;
        this.contaService = contaService;
    }

    @Override
    public Usuario procurarPeloId(String id) {
        return null;
    }

    @Override
    public Usuario criar(Usuario entidade) {
        ValidaCamposEntidade.validar(new ValidaUsuario(entidade, true, usuarioRepositorio), false);
        setValoresPadroes(entidade);
        Usuario usuarioCriado = usuarioRepositorio.save(entidade);
        contaService.criar(new Conta(usuarioCriado, "Carteira"));
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
        if (entidade.getUsuario() != null) {
            usuarioRepositorio.findById(entidade.getUsuario()).orElseThrow(() -> new BadRequestException(USUARIO_NAO_EXISTE));
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
