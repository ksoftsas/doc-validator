package com.ksoft.validation.core.algorithm.country.bo;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class BoliviaValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "CI_BO":
                return new BoliviaCiValidator();
            case "NIT_BO":
                return new BoliviaNitValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para Bolivia: " + documentType);
        }
    }
}
