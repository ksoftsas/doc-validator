package com.ksoft.validation.core.algorithm.country.sv;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class ElSalvadorValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "DUI_SV":
                return new ElSalvadorDuiValidator();
            case "NIT_SV":
                return new ElSalvadorNitValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para El Salvador: " + documentType);
        }
    }
}