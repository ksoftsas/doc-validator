package com.ksoft.validation.core.algorithm.country.cr;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class CostaRicaValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "CI_CR":
                return new CostaRicaCrValidator();
            case "DIMEX_CR":
                return new CostaRicaDimexValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para Costa Rica: " + documentType);
        }
    }
}
