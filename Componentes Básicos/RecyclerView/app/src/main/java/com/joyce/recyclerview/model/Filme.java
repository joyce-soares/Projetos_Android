package com.joyce.recyclerview.model;

public class Filme {

    private String nomeFilme;
    private String genero;
    private String ano;

    public Filme(String nomeFilme, String genero, String ano) {
        this.nomeFilme = nomeFilme;
        this.genero = genero;
        this.ano = ano;
    }

    public Filme() {
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
