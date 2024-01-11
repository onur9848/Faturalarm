package com.senerunosoft.faturalarm.models;

public class TekFazKwh {

    private FaturaSaatKw tekFazSaat;

    public TekFazKwh(FaturaSaatKw tekFazSaat) {
        this.tekFazSaat = tekFazSaat;
    }
    public TekFazKwh() {
    }

    public FaturaSaatKw getTekFazSaat() {
        return tekFazSaat;
    }

    public float getTekFazSaatFarkKw() {
        return tekFazSaat.getFarkKw();
    }

    public void setTekFazSaat(FaturaSaatKw tekFazSaat) {
        this.tekFazSaat = tekFazSaat;
    }
    public void setTekFazSaat(float ilkKw, float sonKw) {
        this.tekFazSaat = new FaturaSaatKw(ilkKw, sonKw);
    }
}
