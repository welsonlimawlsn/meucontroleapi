package br.com.welson.meucontrole.servicos.implemetacoes;

import br.com.welson.meucontrole.excecoes.BadRequestException;
import br.com.welson.meucontrole.persistencia.modelos.AtivacaoConta;
import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import br.com.welson.meucontrole.persistencia.repositorios.AtivacaoContaRepositorio;
import br.com.welson.meucontrole.servicos.AtivacaoContaService;
import br.com.welson.meucontrole.servicos.EmailService;
import br.com.welson.meucontrole.util.AtivacaoContaTemplate;
import br.com.welson.meucontrole.util.ConstantesDeConfiguracao;
import br.com.welson.meucontrole.util.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class AtivacaoContaServiceImpl implements AtivacaoContaService {

    private final AtivacaoContaRepositorio ativacaoContaRepositorio;
    private final EmailService emailService;

    @Autowired
    public AtivacaoContaServiceImpl(AtivacaoContaRepositorio ativacaoContaRepositorio, EmailService emailService) {
        this.ativacaoContaRepositorio = ativacaoContaRepositorio;
        this.emailService = emailService;
    }

    @Override
    public AtivacaoConta criar(Usuario usuario) {
        AtivacaoConta ativacaoConta = new AtivacaoConta(gerarHash(usuario), usuario);
        enviarEmail(usuario, ativacaoConta);
        return ativacaoContaRepositorio.save(ativacaoConta);
    }

    @Override
    public AtivacaoConta pegarAtivacaoConta(String hash) {
        return ativacaoContaRepositorio.findByHash(hash).orElseThrow(() -> new BadRequestException("Link de ativação invalido"));
    }

    @Override
    public AtivacaoConta procurarPeloId(Long id) {
        return null;
    }

    @Override
    public AtivacaoConta criar(AtivacaoConta entidade) {
        return ativacaoContaRepositorio.save(entidade);
    }

    @Override
    public AtivacaoConta alterar(AtivacaoConta entidade) {
        return null;
    }

    @Override
    public void apagar(AtivacaoConta entidade) {

    }

    @Override
    public Iterable<AtivacaoConta> listarTodos() {
        return null;
    }

    private String gerarHash(Usuario usuario) {
        return Hash.encode("MEU_CONTROLE_" + usuario.getEmail() + "_" + ZonedDateTime.now(ZoneOffset.UTC).toInstant());
    }

    private void enviarEmail(Usuario usuario, AtivacaoConta ativacaoConta) {
        emailService.sendSimpleMessage(usuario.getEmail(), "Ativação de conta",
                new AtivacaoContaTemplate(usuario, gerarLink(ativacaoConta)).getHTML());
    }

    private String gerarLink(AtivacaoConta ativacaoConta) {
        return ConstantesDeConfiguracao.ENDERECO_FRONT_END + "/ativacao-conta/" + ativacaoConta.getHash();
    }
}
