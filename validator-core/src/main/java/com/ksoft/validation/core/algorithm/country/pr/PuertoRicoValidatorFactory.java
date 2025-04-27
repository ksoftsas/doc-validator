package com.ksoft.validation.core.algorithm.country.pr;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class PuertoRicoValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "LICENSE_PR":
                return new PuertoRicoLicenseValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para Puerto Rico: " + documentType);
        }
    }
}
