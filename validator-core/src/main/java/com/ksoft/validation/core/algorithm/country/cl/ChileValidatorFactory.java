package com.ksoft.validation.core.algorithm.country.cl;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class ChileValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "RUT_CL":
                return new ChileRutValidator();
            case "CI_CL":
                return new ChileCiValidator();
            case "CEDULA_RESIDENCIA_CL":
                return new ChileCiValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para Chile: " + documentType);
        }
    }
}