package com.ksoft.validation.core.algorithm.country.ve;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class VenezuelaValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "CI_VE":
                return new VenezuelaCiValidator();
            case "RIF_VE":
                return new VenezuelaRifValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para Venezuela: " + documentType);
        }
    }
}