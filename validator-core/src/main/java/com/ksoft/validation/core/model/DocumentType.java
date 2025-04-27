package com.ksoft.validation.core.model;

public enum DocumentType {
    // Argentina
    DNI_AR("DNI_AR"), TI_AR("TI_AR"), CUIL("CUIL"), CUIT("CUIT"),
    // Bolivia
    CI_BO("CI_BO"), NIT_BO("NIT_BO"),
    // Brasil
    RG_BR("RG_BR"), CPF("CPF"), RNE_BR("RNE_BR"), CNPJ("CNPJ"),
    // Chile
    RUT_CL("RUT_CL"), CI_CL("CI_CL"), CEDULA_RESIDENCIA_CL("CEDULA_RESIDENCIA_CL"),
    // Colombia
    NIT_CO("NIT_CO"), CC("CC"), CE_CO("CE_CO"), TI_CO("TI_CO"),
    // Costa Rica
    CI_CR("CI_CR"), DIMEX_CR("DIMEX_CR"),
    // Ecuador
    CI_EC("CI_EC"), CE_EC("CE_EC"), RUC_EC("RUC_EC"),
    // El Salvador
    DUI_SV("DUI_SV"), NIT_SV("NIT_SV"),
    // Guatemala
    DPI_GT("DPI_GT"), NIT_GT("NIT_GT"),
    // Honduras
    RTN_HN("RTN_HN"),
    // México
    CURP("CURP"), RFC("RFC"),
    // Nicaragua
    CI_NI("CI_NI"), NIT_NI("NIT_NI"),
    // Panamá
    CIP_PA("CIP_PA"), RUC_PA("RUC_PA"),
    // Paraguay
    CI_PY("CI_PY"), RUC_PY("RUC_PY"),
    // Perú
    DNI_PE("DNI_PE"), RUC_PE("RUC_PE"),
    // Puerto Rico
    LICENSE_PR("LICENSE_PR"),
    // República Dominicana
    CIE_DO("CIE_DO"), RNC_DO("RNC_DO"),
    // Uruguay
    RUT_UY("RUT_UY"), CI_UY("CI_UY"),
    // Venezuela
    CI_VE("CI_VE"), RIF_VE("RIF_VE"),
    // Otros
    GENERIC_FORMAT("GENERIC_FORMAT"),
    UNKNOWN("UNKNOWN");

    private String code;
    
    DocumentType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
