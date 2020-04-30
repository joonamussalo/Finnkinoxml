package com.example.finnkinoxml;

import java.util.ArrayList;

public class Elokuvateatterit {

    public static ArrayList<Teatterit> teatterilista  = new ArrayList<>();



    public static void addTeatteri(String ID,String name) {
        teatterilista.add(new Teatterit(ID,name));
    }

    public static void getArray() {
        for (int i=0;i<teatterilista.size();i++) {
            System.out.println(teatterilista.get(i));
        }
    }


}
