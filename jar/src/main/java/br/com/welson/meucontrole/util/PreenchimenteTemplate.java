package br.com.welson.meucontrole.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public abstract class PreenchimenteTemplate {

    protected Map<String, String> campos;

    public PreenchimenteTemplate() {
        campos = new HashMap<>();
    }

    protected static String lerHTML(InputStream template) {
        try (BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(template, StandardCharsets.UTF_8))) {
            String linha;
            StringBuilder html = new StringBuilder();
            while ((linha = bufferedInputStream.readLine()) != null) {
                html.append(linha);
            }
            return html.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getHTML(String html) {
        String htmlPreenchido = html;
        for (Map.Entry<String, String> entry : campos.entrySet()) {
            htmlPreenchido = htmlPreenchido.replaceAll("\\{" + entry.getKey() + "}", entry.getValue());
        }
        return htmlPreenchido;
    }

    public abstract String getHTML();
}
