package com.ksoft.validation.core.algorithm.country.dr;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class DominicanCieValidator implements DocumentValidator {
    
    // Formato: 11 dígitos (incluyendo dígito verificador)
    private static final String CIE_PATTERN = "^[0-9]{11}$";
    private static final int[] WEIGHTS = {1, 2, 1, 2, 1, 2, 1, 2, 1, 2};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(CIE_PATTERN)) {
            return false;
        }
        
        // Validar dígito verificador con módulo 10
        String base = cleaned.substring(0, 10);
        char providedDv = cleaned.charAt(10);
        
        char calculatedDv = calculateVerifierDigit(base);
        return providedDv == calculatedDv;
    }
    
    private char calculateVerifierDigit(String base) {
        int sum = 0;
        
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(i));
            int weighted = digit * WEIGHTS[i];
            
            // Sumar dígitos individualmente si el resultado es >= 10
            sum += (weighted >= 10) ? (weighted / 10) + (weighted % 10) : weighted;
        }
        
        int remainder = sum % 10;
        int dvValue = (remainder == 0) ? 0 : 10 - remainder;
        
        return Character.forDigit(dvValue, 10);
    }
    
    public String formatCie(String cie) {
        if (!isValid(cie)) return cie;
        
        return String.format("%s-%s-%s",
            cie.substring(0, 3),
            cie.substring(3, 10),
            cie.substring(10));
    }
    
    public String getProvincia(String cie) {
        if (!isValid(cie)) return "Desconocida";
        
        String codigo = cie.substring(0, 3);
        switch (codigo) {
            case "001": return "Distrito Nacional";
            case "002": return "Azua";
            case "003": return "Bahoruco";
            // ... completar con las 32 provincias
            case "032": return "Santo Domingo";
            default: return "Desconocida";
        }
    }
    
    public boolean isCieTemporal(String cie) {
        if (!isValid(cie)) return false;
        // CIE temporales tienen ciertos patrones en los primeros dígitos
        return cie.startsWith("999") || cie.startsWith("888");
    }
}