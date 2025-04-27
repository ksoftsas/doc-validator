package com.ksoft.validation.core.algorithm.country.cr;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class CostaRicaDimexValidator implements DocumentValidator {
    
    // Formato: 12 dígitos (primero 1 o 2) o formato antiguo 11 dígitos
    private static final String DIMEX_PATTERN = "^[12]?[0-9]{11}$";
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(DIMEX_PATTERN)) {
            return false;
        }
        
        // Validar longitud (11 o 12 dígitos)
        if (cleaned.length() != 11 && cleaned.length() != 12) {
            return false;
        }
        
        return true;
    }
    
    public boolean isNewFormat(String dimex) {
        if (!isValid(dimex)) return false;
        return cleanNumber(dimex).length() == 12;
    }
    
    public String formatDimex(String dimex) {
        if (!isValid(dimex)) return dimex;
        
        String cleaned = cleanNumber(dimex);
        if (cleaned.length() == 12) {
            return String.format("%s-%s-%s", 
                cleaned.substring(0, 3),
                cleaned.substring(3, 7),
                cleaned.substring(7));
        } else {
            return String.format("%s-%s-%s", 
                cleaned.substring(0, 2),
                cleaned.substring(2, 6),
                cleaned.substring(6));
        }
    }
}