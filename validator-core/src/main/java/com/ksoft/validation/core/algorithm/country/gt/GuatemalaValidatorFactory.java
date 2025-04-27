package com.ksoft.validation.core.algorithm.country.gt;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class GuatemalaValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "DPI_GT":
                return new GuatemalaDpiValidator();
            case "NIT_GT":
                return new GuatemalaNitValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para Guatemala: " + documentType);
        }
    }
}
