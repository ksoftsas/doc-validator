package com.ksoft.validation.core.algorithm.country.bo;

import com.ksoft.validation.core.algorithm.BaseDocumentValidator;

public class BoliviaCiValidator extends BaseDocumentValidator {
    @Override
    protected String getDocumentPattern() {
        return "^[0-9]{7,8}$"; // 7 u 8 dígitos (depende del departamento)
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "Cédula de Identidad Boliviana";
    }
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        if (!cleaned.matches(getDocumentPattern())) {
            return false;
        }
        
        // Validación adicional: Primeros 1-2 dígitos representan departamento (1-9 o 01-09)
        int dep = Integer.parseInt(cleaned.substring(0, cleaned.length() == 8 ? 2 : 1));
        return dep >= 1 && dep <= 9; // 9 departamentos en Bolivia
    }
}