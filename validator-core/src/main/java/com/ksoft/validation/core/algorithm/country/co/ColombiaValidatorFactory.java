package com.ksoft.validation.core.algorithm.country.co;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class ColombiaValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "CC":
                return new ColombiaCcValidator();
            case "NIT_CO":
                return new ColombiaNitValidator();
            case "CE_CO":
                return new ColombiaCeValidator();
            case "TI_CO":
                return new ColombiaTiValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para Colombia: " + documentType);
        }
    }
}