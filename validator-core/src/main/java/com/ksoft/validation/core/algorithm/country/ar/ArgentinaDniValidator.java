package com.ksoft.validation.core.algorithm.country.ar;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class ArgentinaDniValidator implements DocumentValidator {
    
    // DNI argentino puede ser de 7 u 8 dígitos (los nuevos tienen 8)
    private static final String DNI_PATTERN = "^[0-9]{7,8}$";
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validación básica de formato
        if (!cleaned.matches(DNI_PATTERN)) {
            return false;
        }
        
        // El DNI argentino no tiene dígito verificador, solo validamos formato
        return true;
    }

    @Override
    public String format(String documentNumber) {
        // Eliminar caracteres no numéricos
        String cleaned = documentNumber.replaceAll("[^0-9]", "");
        
        // Validar formato DNI (8 dígitos)
        if (cleaned.matches("\\d{8}")) {
            return cleaned;
        }
        
        return documentNumber; // Devolver original si no cumple formato
    }

    @Override
    public String getDocumentType() {
        return "Documento Nacional de Identidad (DNI)";
    }
}