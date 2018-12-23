package com.example.user.weatherapp;

public class ToaDo {
    private String KinhDo;
    private String ViDo;

    public ToaDo() {
    }

    public ToaDo(String kinhDo, String viDo) {
        KinhDo = kinhDo;
        ViDo = viDo;
    }

    public String getKinhDo() {
        return KinhDo;
    }

    public void setKinhDo(String kinhDo) {
        KinhDo = kinhDo;
    }

    public String getViDo() {
        return ViDo;
    }

    public void setViDo(String viDo) {
        ViDo = viDo;
    }
}

