package com.ua.eventsfinder.DataBase.SearchHistory;

import android.support.annotation.NonNull;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class SearchHistory {

    @PrimaryKey()
    private  long id;

    @ColumnInfo()
    private String objeto;
    @ColumnInfo()
    private String date;
    @ColumnInfo()
    private  String objetoType;

    public SearchHistory(long id, String objeto, String date, String objetoType) {
        this.id = id;
        this.objeto = objeto;
        this.date = date;
        this.objetoType = objetoType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getObjetoType() {
        return objetoType;
    }

    public void setObjetoType(String objetoType) {
        this.objetoType = objetoType;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
