package com.ksoft.validation.core.algorithm.country.co;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class ColombiaTiValidator implements DocumentValidator {
    
    // Formato: 8-10 dígitos (para menores de edad)
    private static final String TI_PATTERN = "^[0-9]{8,10}$";
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(TI_PATTERN)) {
            return false;
        }
        
        // No tiene dígito verificador estándar
        return true;
    }
    
    public String getTipoTi(String ti) {
        if (!isValid(ti)) return "Inválida";
        
        String cleaned = cleanNumber(ti);
        if (cleaned.length() == 8) {
            return "TI antigua";
        } else if (cleaned.length() == 10) {
            return "TI nueva";
        }
        return "TI especial";
    }

    @Override
    public String format(String documentNumber) {
        // Eliminar caracteres no numéricos
        String cleaned = documentNumber.replaceAll("[^0-9]", "");
        
        // Validar TI (8-10 dígitos)
        if (cleaned.matches("\\d{8,10}")) {
            return cleaned;
        }
        
        return documentNumber; // Devolver original si no cumple formato
    }

    @Override
    public String getDocumentType() {
        return "Tarjeta de Identidad Colombiana (TI)";
    }
}