package com.ksoft.validation.core.algorithm.country.pe;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class PeruDniValidator implements DocumentValidator {
    
    // Formato: 8 dígitos
    private static final String DNI_PATTERN = "^[0-9]{8}$";
    private static final int[] WEIGHTS = {3, 2, 7, 6, 5, 4, 3, 2};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(DNI_PATTERN)) {
            return false;
        }
        
        // Validar dígito verificador
        String base = cleaned.substring(0, 8);
        char providedDv = cleaned.charAt(8);
        
        char calculatedDv = calculateVerifierDigit(base);
        return providedDv == calculatedDv;
    }
    
    private char calculateVerifierDigit(String base) {
        int sum = 0;
        
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(i));
            sum += digit * WEIGHTS[i];
        }
        
        int remainder = sum % 11;
        int dvValue = 11 - remainder;
        
        // Casos especiales para Perú
        if (dvValue == 10) return 'K';
        if (dvValue == 11) return '0';
        
        return Character.forDigit(dvValue, 10);
    }
    
    public String formatDni(String dni) {
        if (!isValid(dni)) return dni;
        
        return dni.substring(0, 8) + "-" + dni.charAt(8);
    }
    
    public String getDepartamento(String dni) {
        if (!isValid(dni)) return "Desconocido";
        
        String codigo = dni.substring(0, 2);
        switch (codigo) {
            case "01": return "Amazonas";
            case "02": return "Áncash";
            case "03": return "Apurímac";
            // ... completar con los 24 departamentos
            case "24": return "Ucayali";
            case "25": return "Extranjero";
            default: return "Desconocido";
        }
    }
    
    public boolean isDniExtranjero(String dni) {
        if (!isValid(dni)) return false;
        return dni.startsWith("25");
    }
}