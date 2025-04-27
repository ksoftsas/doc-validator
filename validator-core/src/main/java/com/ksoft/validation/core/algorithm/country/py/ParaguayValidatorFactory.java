package com.ksoft.validation.core.algorithm.country.py;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class ParaguayValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "CI_PY":
                return new ParaguayCiValidator();
            case "RUC_PY":
                return new ParaguayRucValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para Paraguay: " + documentType);
        }
    }
}
