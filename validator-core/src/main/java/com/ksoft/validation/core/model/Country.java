package com.ksoft.validation.core.model;

public enum Country {
    ARGENTINA("AR"), BOLIVIA("BO"), BRASIL("BR"), CHILE("CL"), COLOMBIA("CO"), COSTA_RICA("CR"), ECUADOR("EC"),
    EL_SALVADOR("SV"), GUATEMALA("GT"), HONDURAS("HN"), MEXICO("MX"), NICARAGUA("NI"), PANAMA("PA"),
    PARAGUAY("PY"), PERU("PE"), PUERTO_RICO("PR"), REPUBLICA_DOMINICANA("DO"), URUGUAY("UY"), VENEZUELA("VE");

    private String code;

    Country(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
