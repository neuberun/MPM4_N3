package com.leo.mensagens;

public class Mensagem {
    private String remetente;
    private String conteudo;

    public Mensagem(String remetente, String conteudo) {
        this.remetente = remetente;
        this.conteudo = conteudo;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getConteudo() {
        return conteudo;
    }
}
