package com.ksoft.validation.core.algorithm.country.hn;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class HondurasValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "RTN_HN":
                return new HondurasRtnValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para Honduras: " + documentType);
        }
    }
}