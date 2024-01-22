package com.senerunosoft.faturalarm.enums;

public enum BundleKeys {
    DOCID("docId"),
    FATURA("fatura"),
    FATURAINPUT("faturaInput"),
    FATURASABITLERI("faturaSabitleri"),
    FATURADETAIL("faturaDetail"),


    ;
    private String value;

    BundleKeys(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
