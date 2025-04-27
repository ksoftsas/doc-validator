package com.ksoft.validation.core.algorithm.country.br;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class BrazilValidatorFactory {
    
    public static DocumentValidator getValidator(String documentType) {
        switch (documentType.toUpperCase()) {
            case "CPF":
                return new BrazilCpfValidator();
            case "CNPJ":
                return new BrazilCnpjValidator();
            case "RG_BR":
                return new BrazilRgValidator();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado para Brasil: " + documentType);
        }
    }
}