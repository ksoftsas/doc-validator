package com.ksoft.validation.core.algorithm.country.co;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class ColombiaNitValidator implements DocumentValidator {
    
    // Formato: 9-10 dígitos + DV (opcional el guión)
    private static final String NIT_PATTERN = "^[0-9]{9,10}-?[0-9]$";
    // Pesos según DIAN
    private static final int[] WEIGHTS = {3, 7, 13, 17, 19, 23, 29, 37, 41, 43, 47, 53, 59, 67, 71};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches("^[0-9]{9,10}[0-9]$")) {
            return false;
        }
        
        // Separar base y dígito verificador
        String base = cleaned.substring(0, cleaned.length() - 1);
        int providedDv = Character.getNumericValue(cleaned.charAt(cleaned.length() - 1));
        
        // Calcular dígito verificador
        int calculatedDv = calculateVerifierDigit(base);
        
        return providedDv == calculatedDv;
    }
    
    private int calculateVerifierDigit(String base) {
        int sum = 0;
        
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(i));
            int weight = WEIGHTS[i % WEIGHTS.length];
            sum += digit * weight;
        }
        
        int remainder = sum % 11;
        
        // Casos especiales según reglas DIAN
        if (remainder == 0 || remainder == 1) {
            return remainder;
        } else {
            return 11 - remainder;
        }
    }
    
    public String formatNit(String nit) {
        if (!isValid(nit)) return nit;
        
        String cleaned = cleanNumber(nit);
        String base = cleaned.substring(0, cleaned.length() - 1);
        char dv = cleaned.charAt(cleaned.length() - 1);
        
        return base + "-" + dv;
    }
}