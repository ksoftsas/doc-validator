package com.ksoft.validation.core.algorithm.country.ni;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class NicaraguaCiValidator implements DocumentValidator {
    
    // Formato: 14 dígitos (incluyendo dígitos verificadores)
    private static final String CI_PATTERN = "^[0-9]{14}$";
    private static final int[] WEIGHTS = {2, 3, 4, 5, 6, 7, 2, 3, 4, 5, 6, 7};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(CI_PATTERN)) {
            return false;
        }
        
        // Validar dígitos verificadores con módulo 11
        String base = cleaned.substring(0, 12);
        String providedDv = cleaned.substring(12);
        
        String calculatedDv = calculateVerifierDigits(base);
        
        return providedDv.equals(calculatedDv);
    }
    
    private String calculateVerifierDigits(String base) {
        // Primer dígito verificador
        int sum1 = 0;
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(i));
            sum1 += digit * WEIGHTS[i];
        }
        int dv1 = 11 - (sum1 % 11);
        if (dv1 == 10) dv1 = 0; // Caso especial para Nicaragua
        if (dv1 == 11) dv1 = 0;
        
        // Segundo dígito verificador (incluye el primer DV en el cálculo)
        int sum2 = 0;
        String baseWithDv1 = base + dv1;
        for (int i = 0; i < baseWithDv1.length(); i++) {
            int digit = Character.getNumericValue(baseWithDv1.charAt(i));
            sum2 += digit * WEIGHTS[i % WEIGHTS.length];
        }
        int dv2 = 11 - (sum2 % 11);
        if (dv2 == 10) dv2 = 0; // Caso especial para Nicaragua
        if (dv2 == 11) dv2 = 0;
        
        return String.valueOf(dv1) + dv2;
    }
    
    public String formatCi(String ci) {
        if (!isValid(ci)) return ci;
        
        return String.format("%s-%s-%s-%s",
            ci.substring(0, 3),
            ci.substring(3, 10),
            ci.substring(10, 12),
            ci.substring(12));
    }
    
    public String getDepartamento(String ci) {
        if (!isValid(ci)) return "Desconocido";
        
        String codigoDepto = ci.substring(0, 2);
        switch (codigoDepto) {
            case "01": return "Boaco";
            case "02": return "Carazo";
            case "03": return "Chinandega";
            // ... completar con los 15 departamentos
            case "15": return "RACCS";
            case "16": return "RACCN";
            default: return "Desconocido";
        }
    }
}