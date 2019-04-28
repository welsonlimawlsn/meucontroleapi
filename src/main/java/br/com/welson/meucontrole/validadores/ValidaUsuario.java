package br.com.welson.meucontrole.validadores;

import br.com.welson.meucontrole.excecoes.BadRequestException;
import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import br.com.welson.meucontrole.persistencia.repositorios.UsuarioRepositorio;

import static br.com.welson.meucontrole.util.ErrorMessages.*;

public class ValidaUsuario implements Validador<Usuario> {

    private Usuario usuario;
    private boolean verificarUsuarioEmail;
    private UsuarioRepositorio usuarioRepositorio;

    public ValidaUsuario(Usuario usuario) {
        this.usuario = usuario;
        verificarUsuarioEmail = false;
    }

    public ValidaUsuario(Usuario usuario, boolean verificarUsuarioEmail, UsuarioRepositorio usuarioRepositorio) {
        this.usuario = usuario;
        this.verificarUsuarioEmail = verificarUsuarioEmail;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public void validar() {
        if (usuario.getUsuario() == null || usuario.getUsuario().isEmpty()) {
            throw new BadRequestException(USUARIO_USUARIO_OBRIGATORIO);
        }
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new BadRequestException(NOME_USUARIO_OBRIGATORIO);
        }
        if (usuario.getSobrenome() == null || usuario.getSobrenome().isEmpty()) {
            throw new BadRequestException(SOBRENOME_USUARIO_OBRIGATORIO);
        }
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            throw new BadRequestException(SENHA_USUARIO_OBRIGATORIO);
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new BadRequestException(EMAIL_USUARIO_OBRIGATORIO);
        }

        // Verificar veracidade do email informado pelo usuario

        if (verificarUsuarioEmail) {
            verificarSeEmailEUsuarioNaoExistem();
        }
    }

    @Override
    public Usuario getEntidade() {
        return usuario;
    }

    private void verificarSeEmailEUsuarioNaoExistem() {
        if (usuarioRepositorio.findByUsuario(usuario.getUsuario()).isPresent()) {
            throw new BadRequestException(USUARIO_USUARIO_DUPLICADO);
        }
        if (usuarioRepositorio.findByEmail(usuario.getEmail()).isPresent()) {
            throw new BadRequestException(EMAIL_USUARIO_DUPLICADO);
        }
    }
}
