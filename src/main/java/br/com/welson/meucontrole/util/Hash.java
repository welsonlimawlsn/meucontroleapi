package br.com.welson.meucontrole.util;

import br.com.welson.meucontrole.excecoes.InternalServerErrorException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static br.com.welson.meucontrole.util.ErrorMessages.ERRO_GERAR_HASH;

public abstract class Hash {

    public static String encode(String value) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte[] digest = algorithm.digest(value.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : digest) {
                hex.append(String.format("%02X", 0xFF & b));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new InternalServerErrorException(ERRO_GERAR_HASH);
        }
    }
}
