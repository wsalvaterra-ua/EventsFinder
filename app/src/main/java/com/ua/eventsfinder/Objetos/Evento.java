package com.ua.eventsfinder.Objetos;

public class Evento extends EventoArtista {


    public Evento(String titulo, String data, String localizacao) {
        this.titulo = titulo;
        this.data = data;
        this.localizacao = localizacao;
    }

    public Evento() {
    }


    @Override
    public String getTitulo() {
        return null;
    }

    @Override
    public String getData() {
        return null;
    }

    @Override
    public String getLocalizacao() {
        return null;
    }
}
