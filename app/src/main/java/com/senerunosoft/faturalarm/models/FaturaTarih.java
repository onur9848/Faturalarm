package com.senerunosoft.faturalarm.models;

import java.util.Date;

public class FaturaTarih {
    private Date ilkOkumaTarihi;
    private Date sonOkumaTarihi;

    public FaturaTarih(Date ilkOkumaTarihi, Date sonOkumaTarihi) {
        this.ilkOkumaTarihi = ilkOkumaTarihi;
        this.sonOkumaTarihi = sonOkumaTarihi;
    }

    public FaturaTarih() {
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

    public int getGunSayisi(){
        return (int) ((sonOkumaTarihi.getTime() - ilkOkumaTarihi.getTime()) / (1000 * 60 * 60 * 24));
    }
}
