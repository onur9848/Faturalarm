package com.senerunosoft.faturalarm.models;

public class FiyatSabitleri {
    float dusukKademeBirimFiyat;
    float yuksekKademeBirimFiyat;
    float kdvOrani;
    float elkVeHvgTktmVer;
    float gunlukKullanimKw;
    float dagitimBedeliOrani;

    public FiyatSabitleri() {
    }

    public FiyatSabitleri(float dusukKademeBirimFiyat, float yuksekKademeBirimFiyat, float kdvOrani, float elkVeHvgTktmVer, float gunlukKullanimKw, float dagitimBedeliOrani) {
        this.dusukKademeBirimFiyat = dusukKademeBirimFiyat;
        this.yuksekKademeBirimFiyat = yuksekKademeBirimFiyat;
        this.kdvOrani = kdvOrani;
        this.elkVeHvgTktmVer = elkVeHvgTktmVer;
        this.gunlukKullanimKw = gunlukKullanimKw;
        this.dagitimBedeliOrani = dagitimBedeliOrani;
    }

    public float getDusukKademeBirimFiyat() {
        return dusukKademeBirimFiyat;
    }

    public void setDusukKademeBirimFiyat(float dusukKademeBirimFiyat) {
        this.dusukKademeBirimFiyat = dusukKademeBirimFiyat;
    }

    public float getYuksekKademeBirimFiyat() {
        return yuksekKademeBirimFiyat;
    }

    public void setYuksekKademeBirimFiyat(float yuksekKademeBirimFiyat) {
        this.yuksekKademeBirimFiyat = yuksekKademeBirimFiyat;
    }

    public float getKdvOrani() {
        return kdvOrani/100;
    }

    public void setKdvOrani(float kdvOrani) {
        this.kdvOrani = kdvOrani;
    }

    public float getElkVeHvgTktmVer() {
        return elkVeHvgTktmVer/100;
    }

    public void setElkVeHvgTktmVer(float elkVeHvgTktmVer) {
        this.elkVeHvgTktmVer = elkVeHvgTktmVer;
    }

    public float getGunlukKullanimKw() {
        return gunlukKullanimKw;
    }

    public void setGunlukKullanimKw(float gunlukKullanimKw) {
        this.gunlukKullanimKw = gunlukKullanimKw;
    }

    public float getDagitimBedeliOrani() {
        return dagitimBedeliOrani;
    }

    public void setDagitimBedeliOrani(float dagitimBedeliOrani) {
        this.dagitimBedeliOrani = dagitimBedeliOrani;
    }
}
