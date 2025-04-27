package com.ksoft.validation.core.algorithm.country.ni;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class NicaraguaValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "CI_NI":
                return new NicaraguaCiValidator();
            case "NIT_NI":
                return new NicaraguaNitValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para Nicaragua: " + documentType);
        }
    }
}
