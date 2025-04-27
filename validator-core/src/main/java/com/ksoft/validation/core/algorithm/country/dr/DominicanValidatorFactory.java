package com.ksoft.validation.core.algorithm.country.dr;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class DominicanValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "CIE_DO":
                return new DominicanCieValidator();
            case "RNC_DO":
                return new DominicanRncValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para República Dominicana: " + documentType);
        }
    }
}
