package br.com.welson.meucontrole.util;

import br.com.welson.meucontrole.excecoes.BadRequestException;
import br.com.welson.meucontrole.persistencia.modelos.Conta;
import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class UsuarioUtil {

    public static void verificarSeContaPerteceAoUsuarioLogado(Conta conta) {
        Usuario usuarioLogado = getUsuarioLogado();
        if (!conta.getUsuario().equals(usuarioLogado)) {
            throw new BadRequestException("Conta invalida");
        }
    }

    public static Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
