package com.example.finnkinoxml;

import androidx.annotation.NonNull;

public class Teatterit {

    String paikka;
    String ID;


    public Teatterit(String ID, String paikka) {
        this.paikka=paikka;
        this.ID=ID;
    }

    public String getID() {
        return ID;
    }

    @NonNull
    @Override
    public String toString() {
        return "ID: "+ ID+ " Paikka: "+paikka;
    }
}
