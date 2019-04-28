package br.com.welson.meucontrole.util;

import br.com.welson.meucontrole.persistencia.modelos.Usuario;

public class RecuperacaoSenhaTemplate extends PreenchimenteTemplate {

    private final static String HTML;

    static {
        HTML = lerHTML(new TemplateEmail("recuperacaoSenha").getTemplate());
    }

    public RecuperacaoSenhaTemplate(Usuario usuario, String link) {
        super();
        campos.put("nome", usuario.getNome());
        campos.put("sobrenome", usuario.getSobrenome());
        campos.put("link", link);
    }

    @Override
    public String getHTML() {
        return getHTML(HTML);
    }
}
