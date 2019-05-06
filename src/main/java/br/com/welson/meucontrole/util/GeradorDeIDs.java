package br.com.welson.meucontrole.util;

import java.util.Random;

public class GeradorDeIDs {

    private static char[] caracteres;

    static {
        caracteres = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    }

    private GeradorDeIDs() {
    }

    public static String gerar() {
        char[] id = new char[15];
        Random random = new Random();
        for (int i = 0; i < id.length; i++) {
            id[i] = caracteres[random.nextInt(caracteres.length)];
        }
        return new String(id);
    }

    public static void main(String[] args) {
        System.out.println(gerar());
    }
}
