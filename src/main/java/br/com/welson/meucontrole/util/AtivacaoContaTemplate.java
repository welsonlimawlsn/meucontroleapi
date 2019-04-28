package br.com.welson.meucontrole.util;

import br.com.welson.meucontrole.persistencia.modelos.Usuario;

public class AtivacaoContaTemplate extends PreenchimenteTemplate {

    protected final static String HTML;

    static {
        HTML = lerHTML(new TemplateEmail("ativacaoConta").getTemplate());
    }

    public AtivacaoContaTemplate(Usuario usuario, String link) {
        super();
        campos.put("nome", usuario.getNome());
        campos.put("sobrenome", usuario.getSobrenome());
        campos.put("link", link);
    }

    @Override
    public String getHTML() {
        return super.getHTML(HTML);
    }
}
