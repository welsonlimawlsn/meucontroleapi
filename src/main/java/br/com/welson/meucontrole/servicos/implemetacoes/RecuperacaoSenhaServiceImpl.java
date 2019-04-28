package br.com.welson.meucontrole.servicos.implemetacoes;

import br.com.welson.meucontrole.excecoes.NotFoundException;
import br.com.welson.meucontrole.persistencia.modelos.NovaSenha;
import br.com.welson.meucontrole.persistencia.modelos.RecuperacaoSenha;
import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import br.com.welson.meucontrole.persistencia.repositorios.RecuperacaoSenhaRepositorio;
import br.com.welson.meucontrole.servicos.EmailService;
import br.com.welson.meucontrole.servicos.RecuperacaoSenhaService;
import br.com.welson.meucontrole.servicos.UsuarioService;
import br.com.welson.meucontrole.util.Hash;
import br.com.welson.meucontrole.util.RecuperacaoSenhaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static br.com.welson.meucontrole.util.ConstantesDeConfiguracao.ENDERECO_FRONT_END;

@Service
public class RecuperacaoSenhaServiceImpl implements RecuperacaoSenhaService {

    private final RecuperacaoSenhaRepositorio recuperacaoSenhaRepositorio;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Autowired
    public RecuperacaoSenhaServiceImpl(RecuperacaoSenhaRepositorio recuperacaoSenhaRepositorio, EmailService emailService, UsuarioService usuarioService) {
        this.recuperacaoSenhaRepositorio = recuperacaoSenhaRepositorio;
        this.emailService = emailService;
        this.usuarioService = usuarioService;
    }

    @Override
    public RecuperacaoSenha getRecuperacaoSenhaSeExistir(String hash) {
        RecuperacaoSenha recuperacaoSenha = recuperacaoSenhaRepositorio.findByHash(hash).orElseThrow(() -> new NotFoundException("Link invalido!"));
        if (verificarValidade(recuperacaoSenha) || recuperacaoSenha.getUsado()) {
            throw new NotFoundException("Link Invalido");
        }
        return recuperacaoSenha;
    }

    private boolean verificarValidade(RecuperacaoSenha recuperacaoSenha) {
        return recuperacaoSenha.getValidade().isBefore(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
    }

    @Override
    public boolean recuperarSenha(String hash, NovaSenha novaSenha) {
        RecuperacaoSenha recuperacaoSenha = getRecuperacaoSenhaSeExistir(hash);
        recuperacaoSenha.setUsado(true);
        alterar(recuperacaoSenha);
        recuperacaoSenha.getUsuario().setSenha(new BCryptPasswordEncoder().encode(novaSenha.getSenha()));
        usuarioService.alterar(recuperacaoSenha.getUsuario());
        return true;
    }

    @Override
    public RecuperacaoSenha criar(String email) {
        Usuario usuario = usuarioService.getUsuarioPorEmail(email);
        if (usuario != null) {
            RecuperacaoSenha recuperacaoSenha = new RecuperacaoSenha(criarHash(usuario), ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime().plusMinutes(30), false, usuario);
            criar(recuperacaoSenha);
            enviarEmail(recuperacaoSenha);
            return recuperacaoSenha;
        }
        return null;
    }

    @Override
    public RecuperacaoSenha procurarPeloId(Long id) {
        return null;
    }

    @Override
    public RecuperacaoSenha criar(RecuperacaoSenha entidade) {
        //Fazer validações
        return recuperacaoSenhaRepositorio.save(entidade);
    }

    @Override
    public RecuperacaoSenha alterar(RecuperacaoSenha entidade) {
        //Fazer validações
        return recuperacaoSenhaRepositorio.save(entidade);
    }

    @Override
    public void apagar(RecuperacaoSenha entidade) {

    }

    @Override
    public Iterable<RecuperacaoSenha> listarTodos() {
        return null;
    }

    private String criarHash(Usuario usuario) {
        return Hash.encode("MEU_CONTROLE_" + usuario.getEmail() + "_" + LocalDateTime.now().toInstant(ZoneOffset.UTC));
    }

    private void enviarEmail(RecuperacaoSenha recuperacaoSenha) {
        emailService.sendSimpleMessage(
                recuperacaoSenha.getUsuario().getEmail(),
                "Recuperação de Senha",
                new RecuperacaoSenhaTemplate(
                        recuperacaoSenha.getUsuario(),
                        ENDERECO_FRONT_END + "/recuperar-senha/" + recuperacaoSenha.getHash()).getHTML());
    }
}
