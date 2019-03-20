package br.com.welson.meucontrole.validadores;

import br.com.welson.meucontrole.excecoes.BadRequestException;
import br.com.welson.meucontrole.persistencia.modelos.Entidade;
import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import br.com.welson.meucontrole.persistencia.repositorios.UsuarioRepositorio;

public class ValidaUsuario implements Validador {

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
            throw new BadRequestException("Um nome de usuario é obrigatório.");
        }
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new BadRequestException("Um nome é obrigatório.");
        }
        if (usuario.getSobrenome() == null || usuario.getSobrenome().isEmpty()) {
            throw new BadRequestException("Um sobrenome é obrigatório.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            throw new BadRequestException("Você deve criar uma senha.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new BadRequestException("Você deve informar o seu e-mail.");
        }

        if (verificarUsuarioEmail) {
            verificarSeEmailEUsuarioNaoExistem();
        }
    }

    @Override
    public Entidade getEntidade() {
        return usuario;
    }

    private void verificarSeEmailEUsuarioNaoExistem() {
        if (usuarioRepositorio.findByUsuario(usuario.getUsuario()).isPresent()) {
            throw new BadRequestException("Este nome usuario já existe. Por favor, escolha outro.");
        }
        if (usuarioRepositorio.findByEmail(usuario.getEmail()).isPresent()) {
            throw new BadRequestException("Esse email já está sendo utilizado. Por favor, faça o login.");
        }
    }
}
