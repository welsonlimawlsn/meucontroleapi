package br.com.welson.meucontrole.servicos;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);
}
