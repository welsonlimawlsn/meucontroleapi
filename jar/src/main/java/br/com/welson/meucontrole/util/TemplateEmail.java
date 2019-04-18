package br.com.welson.meucontrole.util;

import java.io.InputStream;

public class TemplateEmail {

    private InputStream template;

    public TemplateEmail(String template) {
        this.template =
                getClass().getClassLoader().getResourceAsStream("templates/" + template + (template.endsWith(".html") ? "" : ".html"));
    }

    public InputStream getTemplate() {
        return template;
    }
}
