package com.ksoft.validation.core.algorithm.country.ec;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class EcuadorValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "CI_EC":
                return new EcuadorCiValidator();
            case "RUC_EC":
                return new EcuadorRucValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para Ecuador: " + documentType);
        }
    }
}
