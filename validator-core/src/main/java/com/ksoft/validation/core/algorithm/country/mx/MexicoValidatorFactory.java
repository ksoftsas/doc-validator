package com.ksoft.validation.core.algorithm.country.mx;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class MexicoValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "CURP":
                return new MexicoCurpValidator();
            case "RFC":
                return new MexicoRfcValidator();
            case "NSS":
                return new MexicoNssValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para México: " + documentType);
        }
    }
}
