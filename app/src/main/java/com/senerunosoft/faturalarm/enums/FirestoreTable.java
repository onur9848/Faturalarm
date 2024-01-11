package com.senerunosoft.faturalarm.enums;

public enum FirestoreTable {
    FATURAINPUTS("faturaInputs"),
    FATURASABITLERI("faturaSabitleri"),
    FATURA("fatura");

    private String value;

    FirestoreTable(String value) {
        this.value = value;
    }

    public String getValue() {
        boolean test = true;
        if (test) {
            return "test" + value;
        }
        return value;
    }
}
