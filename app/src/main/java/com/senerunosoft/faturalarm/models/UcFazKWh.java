package com.senerunosoft.faturalarm.models;

public class UcFazKWh {

    private FaturaSaatKw gunduzSaat;
    private FaturaSaatKw puantSaat;
    private FaturaSaatKw geceSaat;

    public UcFazKWh() {
    }

    public UcFazKWh(FaturaSaatKw gunduzSaat, FaturaSaatKw puantSaat, FaturaSaatKw geceSaat) {
        this.gunduzSaat = gunduzSaat;
        this.puantSaat = puantSaat;
        this.geceSaat = geceSaat;
    }

    public FaturaSaatKw getGunduzSaat() {
        return gunduzSaat;
    }

    public float getGunduzSaatFarkKw() {
        return gunduzSaat.getFarkKw();
    }

    public void setGunduzSaat(FaturaSaatKw gunduzSaat) {
        this.gunduzSaat = gunduzSaat;
    }

    public void setGunduzSaat(float ilkKw, float sonKw) {
        this.gunduzSaat = new FaturaSaatKw(ilkKw, sonKw);
    }

    public FaturaSaatKw getPuantSaat() {
        return puantSaat;
    }

    public float getPuantSaatFarkKw() {
        return puantSaat.getFarkKw();
    }

    public void setPuantSaat(FaturaSaatKw puantSaat) {
        this.puantSaat = puantSaat;
    }

    public void setPuantSaat(float ilkKw, float sonKw) {
        this.puantSaat = new FaturaSaatKw(ilkKw, sonKw);
    }

    public FaturaSaatKw getGeceSaat() {
        return geceSaat;
    }

    public float getGeceSaatFarkKw() {
        return geceSaat.getFarkKw();
    }

    public void setGeceSaat(FaturaSaatKw geceSaat) {
        this.geceSaat = geceSaat;
    }

    public void setGeceSaat(float ilkKw, float sonKw) {
        this.geceSaat = new FaturaSaatKw(ilkKw, sonKw);
    }

    public float getFarkKw() {
        return gunduzSaat.getFarkKw() + puantSaat.getFarkKw() + geceSaat.getFarkKw();
    }
    public TekFazKwh getTekFazKwh(){
        float ilkKw = gunduzSaat.getIlkKw() + puantSaat.getIlkKw() + geceSaat.getIlkKw();
        float sonKw = gunduzSaat.getSonKw() + puantSaat.getSonKw() + geceSaat.getSonKw();
        return new TekFazKwh(new FaturaSaatKw(ilkKw, sonKw));
    }
}
