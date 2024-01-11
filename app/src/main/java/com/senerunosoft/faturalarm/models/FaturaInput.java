package com.senerunosoft.faturalarm.models;

import java.util.Date;

public class FaturaInput {
    private String id;
    private String faturaName;
    private Date faturaKayitTarihi;
    private Date ilkOkumaTarihi;
    private Date sonOkumaTarihi;
    private boolean isThreeFaz;
    private float tekZamanIlkOkuma;
    private float tekZamanSonOkuma;
    private float gunduzIlkOkuma;
    private float gunduzSonOkuma;
    private float puantIlkOkuma;
    private float puantSonOkuma;
    private float geceIlkOkuma;
    private float geceSonOkuma;
    private float dusukKademeBirimFiyat = 1.34107f;
    private float yuksekKademeBirimFiyat = 1.991154f;
    private float vergiOrani = 10f;
    private float elkVeHvgTktmVer = 5f;
    private float gunlukKullanimKw = 8f;
    private float dagitimBedeliOrani = 0.858883f;

    public FaturaInput() {
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

    public Date getFaturaKayitTarihi() {
        return faturaKayitTarihi;
    }

    public void setFaturaKayitTarihi(Date faturaKayitTarihi) {
        this.faturaKayitTarihi = faturaKayitTarihi;
    }

    public Date getIlkOkumaTarihi() {
        return ilkOkumaTarihi;
    }

    public void setIlkOkumaTarihi(Date ilkOkumaTarihi) {
        this.ilkOkumaTarihi = ilkOkumaTarihi;
    }

    public Date getSonOkumaTarihi() {
        return sonOkumaTarihi;
    }

    public void setSonOkumaTarihi(Date sonOkumaTarihi) {
        this.sonOkumaTarihi = sonOkumaTarihi;
    }

    public boolean isThreeFaz() {
        return isThreeFaz;
    }

    public void setThreeFaz(boolean threeFaz) {
        isThreeFaz = threeFaz;
    }

    public float getTekZamanIlkOkuma() {
        return tekZamanIlkOkuma;
    }

    public void setTekZamanIlkOkuma(float tekZamanIlkOkuma) {
        this.tekZamanIlkOkuma = tekZamanIlkOkuma;
    }

    public float getTekZamanSonOkuma() {
        return tekZamanSonOkuma;
    }

    public void setTekZamanSonOkuma(float tekZamanSonOkuma) {
        this.tekZamanSonOkuma = tekZamanSonOkuma;
    }

    public float getGunduzIlkOkuma() {
        return gunduzIlkOkuma;
    }

    public void setGunduzIlkOkuma(float gunduzIlkOkuma) {
        this.gunduzIlkOkuma = gunduzIlkOkuma;
    }

    public float getGunduzSonOkuma() {
        return gunduzSonOkuma;
    }

    public void setGunduzSonOkuma(float gunduzSonOkuma) {
        this.gunduzSonOkuma = gunduzSonOkuma;
    }

    public float getPuantIlkOkuma() {
        return puantIlkOkuma;
    }

    public void setPuantIlkOkuma(float puantIlkOkuma) {
        this.puantIlkOkuma = puantIlkOkuma;
    }

    public float getPuantSonOkuma() {
        return puantSonOkuma;
    }

    public void setPuantSonOkuma(float puantSonOkuma) {
        this.puantSonOkuma = puantSonOkuma;
    }

    public float getGeceIlkOkuma() {
        return geceIlkOkuma;
    }

    public void setGeceIlkOkuma(float geceIlkOkuma) {
        this.geceIlkOkuma = geceIlkOkuma;
    }

    public float getGeceSonOkuma() {
        return geceSonOkuma;
    }

    public void setGeceSonOkuma(float geceSonOkuma) {
        this.geceSonOkuma = geceSonOkuma;
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

    public float getVergiOrani() {
        return vergiOrani;
    }

    public void setVergiOrani(float vergiOrani) {
        this.vergiOrani = vergiOrani;
    }

    public float getElkVeHvgTktmVer() {
        return elkVeHvgTktmVer;
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
