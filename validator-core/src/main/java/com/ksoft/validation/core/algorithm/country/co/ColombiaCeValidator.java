package com.ksoft.validation.core.algorithm.country.co;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class ColombiaCeValidator implements DocumentValidator {
    
    // Formato: 6-7 dígitos (antiguas podrían tener letras)
    private static final String CE_PATTERN = "^[A-Za-z0-9]{6,7}$";
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(CE_PATTERN)) {
            return false;
        }
        
        // No hay algoritmo estándar de validación
        return true;
    }
    
    public boolean isNumericCe(String ce) {
        String cleaned = cleanNumber(ce);
        return cleaned.matches("^[0-9]{6,7}$");
    }

    @Override
    public String format(String documentNumber) {
        // Eliminar caracteres no numéricos
        String cleaned = documentNumber.replaceAll("[^0-9]", "");
        
        // Validar CE (6-10 dígitos)
        if (cleaned.matches("\\d{6,10}")) {
            return cleaned;
        }
        
        return documentNumber; // Devolver original si no cumple formato
    }

    @Override
    public String getDocumentType() {
        return "Cédula de Extranjería Colombiana (CE)";
    }
}