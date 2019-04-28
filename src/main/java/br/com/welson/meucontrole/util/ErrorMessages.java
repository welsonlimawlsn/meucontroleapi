package br.com.welson.meucontrole.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessages {
    USUARIO_USUARIO_OBRIGATORIO("Um nome de usuario é obrigatório."),
    LINK_ATIVACAO_INVALIDO("Link de ativação invalido"),
    ERRO_GERAR_HASH("Erro ao gerar o hash"),
    CATEGORIA_NAO_EXISTE("Essa categoria não existe"),
    CONTA_NAO_ENCONTRADA("Conta não encontrada"),
    LINK_INVALIDO("Link Invalido"),
    USUARIO_NAO_EXISTE("Esse usuario não existe."),
    TOKEN_EXPIRADO("Seu token expirou! Faça o login novamente."),
    PAGINA_NAO_EXISTE("Essa pagina não existe"),
    NOME_USUARIO_OBRIGATORIO("Um nome é obrigatório."),
    SOBRENOME_USUARIO_OBRIGATORIO("Um sobrenome é obrigatório."),
    SENHA_USUARIO_OBRIGATORIO("Você deve criar uma senha."),
    EMAIL_USUARIO_OBRIGATORIO("Você deve informar o seu e-mail."),
    USUARIO_USUARIO_DUPLICADO("Este usuário já existe!"),
    EMAIL_USUARIO_DUPLICADO("Este e-mail já está sendo utilizado, faça o login!"),
    ID_ENTIDADE_NULO("VocÊ não deve informar ID para criar esse objeto."),
    ID_ENTIDADE_NAO_NULO("Você precisa informar um ID."),
    NOME_CATEGORIA_OBRIGATORIO("Você deve dar um nome a categoria!"),
    NOME_CONTA_OBRIGATORIO("Um nome para a conta é obrigatório"),
    VALOR_MOVIMENTACAO_DIFERENTE_ZERO("O valor da movimentação precisa ser diferente de 0."),
    MOVIMENTACAO_DEVE_ASSOCIADA_CONTA("A movimentação precisa está associada a uma conta."),
    DESCRICAO_MOVIMENTACAO_OBRIGATORIA("A movimentação precisa ter uma descrição."),
    MOVIMENTACAO_DEVE_ASSOCIADA_CATEGORIA("A movimentação precisa ter uma categoria."),
    DATA_INICIAL_MOVIMENTACAO_OBRIGATORIA("A movimentação precisa ter uma data inicial."),
    MOVIMENTACAO_PRECISA_MAIS_DE_UMA_PARCELA("A movimentação precisa ter mais de uma parcela");

    private String message;
}
