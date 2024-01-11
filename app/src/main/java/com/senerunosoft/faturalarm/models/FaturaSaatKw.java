package com.senerunosoft.faturalarm.models;

import java.util.HashMap;

public class FaturaSaatKw {
    private float ilkKw=0;
    private float sonKw=0;
    private float farkKw;

    public FaturaSaatKw() {
    }

    public FaturaSaatKw(float ilkKw, float sonKw) {
        this.ilkKw = ilkKw;
        this.sonKw = sonKw;
    }

    public FaturaSaatKw(HashMap<String, Object> tekZamanKw) {
        this.ilkKw = Float.parseFloat(tekZamanKw.get("ilkKw").toString());
        this.sonKw = Float.parseFloat(tekZamanKw.get("sonKw").toString());
    }

    public float getIlkKw() {
        return ilkKw;
    }

    public void setIlkKw(float ilkKw) {
        this.ilkKw = ilkKw;
    }

    public float getSonKw() {
        return sonKw;
    }

    public void setSonKw(float sonKw) {
        this.sonKw = sonKw;
    }

    public float getFarkKw() {
        farkKw = sonKw - ilkKw;
        return farkKw;
    }

}
