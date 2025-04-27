package com.ksoft.validation.core.algorithm.country.pe;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class PeruValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "DNI_PE":
                return new PeruDniValidator();
            case "RUC_PE":
                return new PeruRucValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para Perú: " + documentType);
        }
    }
}
