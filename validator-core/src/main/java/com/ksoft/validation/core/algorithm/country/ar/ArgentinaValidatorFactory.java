package com.ksoft.validation.core.algorithm.country.ar;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class ArgentinaValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "DNI_AR":
                return new ArgentinaDniValidator();
            case "CUIL":
                return new ArgentinaCuitCuilValidator();
            case "CUIT":
                return new ArgentinaCuitCuilValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para Argentina: " + documentType);
        }
    }
}
