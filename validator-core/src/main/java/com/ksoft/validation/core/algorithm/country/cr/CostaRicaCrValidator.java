package com.ksoft.validation.core.algorithm.country.cr;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class CostaRicaCrValidator implements DocumentValidator {
    
    // Formato: 1-12 dígitos (generalmente 9 dígitos para cédulas físicas)
    private static final String CR_PATTERN = "^[0-9]{1,12}$";
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(CR_PATTERN)) {
            return false;
        }
        
        // Validaciones adicionales para cédulas físicas (9 dígitos)
        if (cleaned.length() == 9) {
            return validatePhysicalCr(cleaned);
        }
        
        return true;
    }
    
    private boolean validatePhysicalCr(String crNumber) {
        // Algoritmo de validación para cédulas físicas de 9 dígitos
        try {
            int sum = 0;
            for (int i = 0; i < 8; i++) {
                int digit = Character.getNumericValue(crNumber.charAt(i));
                int weight = (i % 2 == 0) ? 1 : 2;
                int product = digit * weight;
                sum += (product > 9) ? product - 9 : product;
            }
            
            int calculatedDv = (10 - (sum % 10)) % 10;
            int providedDv = Character.getNumericValue(crNumber.charAt(8));
            
            return calculatedDv == providedDv;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String formatCr(String cr) {
        if (!isValid(cr)) return cr;
        
        String cleaned = cleanNumber(cr);
        if (cleaned.length() == 9) {
            return String.format("%s-%s-%s", 
                cleaned.substring(0, 1),
                cleaned.substring(1, 5),
                cleaned.substring(5));
        }
        return cleaned;
    }
}