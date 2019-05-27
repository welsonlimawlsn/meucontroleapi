package br.com.welson.meucontrole.util;

import java.net.URI;

public final class URLBackEnd {

    private URLBackEnd() {
    }

    public static URI comporURL(String... partes) {
        StringBuilder url = new StringBuilder();
        url.append(ConstantesDeConfiguracao.ENDERECO_API);
        for (String parte : partes) {
            if (!parte.startsWith("/"))
                parte = "/" + parte;
            if (parte.endsWith("/"))
                parte = parte.substring(0, parte.length() - 1);
            if (parte.substring(1).contains("/"))
                throw new IllegalArgumentException("Uma não deve ter o caractere de \"/\" no meio da parte, a não ser que seja o primeiro caractere.");
            url.append(parte);
        }
        return URI.create(url.toString());
    }
}
