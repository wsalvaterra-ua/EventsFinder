package com.ua.eventsfinder.Objetos;

public abstract class EventoArtista {

    public String titulo;
    public String data;
    public String localizacao;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public abstract String getTitulo();

    public abstract String getData();

    public abstract String getLocalizacao();

}
