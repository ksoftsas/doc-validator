package com.ksoft.validation.core.algorithm.country.pa;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class PanamaValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "CIP_PA":
                return new PanamaCipValidator();
            case "RUC_PA":
                return new PanamaRucValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para Panamá: " + documentType);
        }
    }
}