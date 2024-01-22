package com.senerunosoft.faturalarm.models;

import java.time.Instant;
import java.util.Date;

public class Fatura {

    private String id;
    private String faturaName;
    private FaturaTarih faturaTarih;
    private boolean isThreeFaz;
    private TekFazKwh tekFazKwh;
    private UcFazKWh ucFazKwh;
    private FiyatSabitleri sabitler;

    public Fatura() {
        sabitler = new FiyatSabitleri(1.34107f, 1.991154f, 10f, 5f, 8f, 0.858883f);
    }

    public Fatura(FaturaInput input) {
        this.faturaName = input.getFaturaName();
        this.faturaTarih = new FaturaTarih(input.getIlkOkumaTarihi(), input.getSonOkumaTarihi());
        this.isThreeFaz = input.isThreeFaz();
        if (isThreeFaz) {
            FaturaSaatKw gunduzSaat = new FaturaSaatKw(input.getGunduzIlkOkuma(), input.getGunduzSonOkuma());
            FaturaSaatKw puantSaat = new FaturaSaatKw(input.getPuantIlkOkuma(), input.getPuantSonOkuma());
            FaturaSaatKw geceSaat = new FaturaSaatKw(input.getGeceIlkOkuma(), input.getGeceSonOkuma());
            this.ucFazKwh = new UcFazKWh(gunduzSaat, puantSaat, geceSaat);
        } else {
            FaturaSaatKw tekFazSaat = new FaturaSaatKw(input.getTekZamanIlkOkuma(), input.getTekZamanSonOkuma());
            this.tekFazKwh = new TekFazKwh(tekFazSaat);
        }
        this.sabitler = new FiyatSabitleri(input.getDusukKademeBirimFiyat(), input.getYuksekKademeBirimFiyat(), input.getVergiOrani(), input.getElkVeHvgTktmVer(), input.getGunlukKullanimKw(), input.getDagitimBedeliOrani());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFaturaName() {
        return faturaName;
    }

    public void setFaturaName(String faturaName) {
        this.faturaName = faturaName;
    }

    public FaturaTarih getFaturaTarih() {
        return faturaTarih;
    }

    public void setFaturaTarih(FaturaTarih faturaTarih) {
        this.faturaTarih = faturaTarih;
    }

    public boolean isThreeFaz() {
        return isThreeFaz;
    }

    public void setThreeFaz(boolean threeFaz) {
        isThreeFaz = threeFaz;
    }

    public TekFazKwh getTekFazKwh() {
        return tekFazKwh;
    }

    public void setTekFazKwh(TekFazKwh tekFazKwh) {
        this.tekFazKwh = tekFazKwh;
    }

    public UcFazKWh getUcFazKwh() {
        return ucFazKwh;
    }

    public void setUcFazKwh(UcFazKWh ucFazKwh) {
        this.ucFazKwh = ucFazKwh;
    }

    public float getKdvsizTutar() {
        return getKdvsizTutar(sabitler.getDusukKademeBirimFiyat(), sabitler.getYuksekKademeBirimFiyat());
    }

    public float getKdvsizTutar(float dusukKademeBirimFiyat, float yuksekKademeBirimFiyat) {
        int gunSayisi = faturaTarih.getGunSayisi();
        float kdvsizTutar = 0;
        float dusukKademeLimit = gunSayisi * sabitler.gunlukKullanimKw;
        float toplamKwh = getToplamKwh();

        if (toplamKwh <= dusukKademeLimit) {
            kdvsizTutar = toplamKwh * dusukKademeBirimFiyat;
        } else {
            kdvsizTutar = (dusukKademeLimit * dusukKademeBirimFiyat) + ((toplamKwh - dusukKademeLimit) * yuksekKademeBirimFiyat);
        }

        return kdvsizTutar;
    }

    public float getToplamKwh() {
        if (isThreeFaz) {
            return ucFazKwh.getFarkKw();
        } else {
            return tekFazKwh.getTekFazSaatFarkKw();
        }
    }

    public float getDagitimBedeli() {
        return getToplamKwh() * sabitler.getDagitimBedeliOrani();
    }

    public float getElktVeHvg() {
        return (getKdvsizTutar() - getDagitimBedeli()) * sabitler.getElkVeHvgTktmVer();
    }

    public float getKdvliTutar() {
        return getKdvliTutar(sabitler.getDusukKademeBirimFiyat(), sabitler.getYuksekKademeBirimFiyat());
    }

    public float getKdvliTutar(float dusukKademeBirimFiyat, float yuksekKademeBirimFiyat) {
        float tutar = 0;
        tutar += getKdvsizTutar(dusukKademeBirimFiyat, yuksekKademeBirimFiyat) + getElktVeHvg();
        return tutar * (sabitler.getKdvOrani() + 1);
    }

    public FaturaInput getFaturaInputs(String faturaName) {
        FaturaInput input = new FaturaInput();
        input.setFaturaName(faturaName);
        input.setFaturaKayitTarihi(Date.from(Instant.now()));
        input.setIlkOkumaTarihi(faturaTarih.getIlkOkumaTarihi());
        input.setSonOkumaTarihi(faturaTarih.getSonOkumaTarihi());
        input.setThreeFaz(isThreeFaz);
        input.setTekZamanIlkOkuma(tekFazKwh.getTekFazSaat().getIlkKw());
        input.setTekZamanSonOkuma(tekFazKwh.getTekFazSaat().getSonKw());
        input.setGunduzIlkOkuma(ucFazKwh.getGunduzSaat().getIlkKw());
        input.setGunduzSonOkuma(ucFazKwh.getGunduzSaat().getSonKw());
        input.setPuantIlkOkuma(ucFazKwh.getPuantSaat().getIlkKw());
        input.setPuantSonOkuma(ucFazKwh.getPuantSaat().getSonKw());
        input.setGeceIlkOkuma(ucFazKwh.getGeceSaat().getIlkKw());
        input.setGeceSonOkuma(ucFazKwh.getGeceSaat().getSonKw());
        return input;
    }

    public FaturaInput getFaturaInputs(String faturaName, FiyatSabitleri sabitler) {
        FaturaInput input = getFaturaInputs(faturaName);
        input.setDusukKademeBirimFiyat(sabitler.getDusukKademeBirimFiyat());
        input.setYuksekKademeBirimFiyat(sabitler.getYuksekKademeBirimFiyat());
        input.setVergiOrani(sabitler.getKdvOrani());
        input.setElkVeHvgTktmVer(sabitler.getElkVeHvgTktmVer());
        input.setGunlukKullanimKw(sabitler.getGunlukKullanimKw());
        input.setDagitimBedeliOrani(sabitler.getDagitimBedeliOrani());
        return input;

    }

    public float getKdv() {
        return getKdvliTutar() - (getKdvsizTutar()+getElktVeHvg());
    }

    public float getDusukKademeKullanim(){
        float toplamKwh = getToplamKwh();
        float dusukKademeLimit = faturaTarih.getGunSayisi() * sabitler.gunlukKullanimKw;
        float dusukKademeKullanim = 0;
        if (toplamKwh <= dusukKademeLimit) {
            dusukKademeKullanim = toplamKwh;
        } else {
            dusukKademeKullanim = dusukKademeLimit;
        }
        return dusukKademeKullanim;
    }

    public float getYuksekKademeKullanim(){
        float toplamKwh = getToplamKwh();
        float dusukKademeLimit = faturaTarih.getGunSayisi() * sabitler.gunlukKullanimKw;
        float yuksekKademeKullanim = 0;
        if (toplamKwh > dusukKademeLimit) {
            yuksekKademeKullanim = toplamKwh - dusukKademeLimit;
        }
        return yuksekKademeKullanim;
    }
    public float getDusukKademeToplamFiyat() {
        float toplamKwh = getToplamKwh();
        float dusukKademeLimit = faturaTarih.getGunSayisi() * sabitler.gunlukKullanimKw;
        float dusukKademeToplamFiyat = 0;
        if (toplamKwh <= dusukKademeLimit) {
            dusukKademeToplamFiyat = toplamKwh * sabitler.getDusukKademeBirimFiyat();
        } else {
            dusukKademeToplamFiyat = dusukKademeLimit * sabitler.getDusukKademeBirimFiyat();
        }
        return dusukKademeToplamFiyat;
    }

    public float getYuksekKademeToplamFiyat() {
        float toplamKwh = getToplamKwh();
        float dusukKademeLimit = faturaTarih.getGunSayisi() * sabitler.gunlukKullanimKw;
        float yuksekKademeToplamFiyat = 0;
        if (toplamKwh > dusukKademeLimit) {
            yuksekKademeToplamFiyat = (toplamKwh - dusukKademeLimit) * sabitler.getYuksekKademeBirimFiyat();
        }
        return yuksekKademeToplamFiyat;
    }
}
