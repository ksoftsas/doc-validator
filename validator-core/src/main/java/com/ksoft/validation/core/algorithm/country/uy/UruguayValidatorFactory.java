package com.ksoft.validation.core.algorithm.country.uy;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class UruguayValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "CI_UY":
                return new UruguayCiValidator();
            case "RUT_UY":
                return new UruguayRutValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para Uruguay: " + documentType);
        }
    }
}
