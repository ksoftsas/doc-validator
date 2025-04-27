package com.ksoft.validation.core.algorithm.country.ve;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class VenezuelaCiValidator implements DocumentValidator {
    
    // Formato: V-12345678 o E-12345678 (8 dígitos)
    private static final String CI_PATTERN = "^[VEve]-?[0-9]{7,8}$";
    private static final int[] WEIGHTS = {3, 2, 7, 6, 5, 4, 3, 2};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanCiNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(CI_PATTERN)) {
            return false;
        }
        
        // Extraer partes
        char type = cleaned.charAt(0);
        String numbers = cleaned.substring(1);
        
        // Validar dígito verificador
        char providedDv = numbers.charAt(numbers.length() - 1);
        String base = numbers.substring(0, numbers.length() - 1);
        
        char calculatedDv = calculateVerifierDigit(base);
        return providedDv == calculatedDv;
    }
    
    private String cleanCiNumber(String ci) {
        if (ci == null) return "";
        // Eliminar espacios y guiones, convertir a mayúsculas
        return ci.replaceAll("[^VEve0-9]", "").toUpperCase();
    }
    
    private char calculateVerifierDigit(String base) {
        int sum = 0;
        
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(i));
            sum += digit * WEIGHTS[i];
        }
        
        int remainder = sum % 11;
        int dvValue = 11 - remainder;
        
        // Casos especiales para Venezuela
        if (dvValue == 11) return '0';
        if (dvValue == 10) {
            // Para cédulas de extranjeros (E-) el DV es '9' cuando el cálculo da 10
            return '9';
        }
        
        return Character.forDigit(dvValue, 10);
    }
    
    public String formatCi(String ci) {
        String cleaned = cleanCiNumber(ci);
        
        if (!isValid(cleaned)) {
            return ci;
        }
        
        return String.format("%s-%s",
            cleaned.substring(0, 1),
            cleaned.substring(1));
    }
    
    public String getTipoDocumento(String ci) {
        String cleaned = cleanCiNumber(ci);
        
        if (!isValid(cleaned)) {
            return "Desconocido";
        }
        
        return cleaned.charAt(0) == 'V' ? 
            "Cédula Venezolana" : "Cédula de Extranjero";
    }
    
    public String getEstadoFromCi(String ci) {
        if (!isValid(ci)) return "Desconocido";
        
        String cleaned = cleanCiNumber(ci);
        if (cleaned.length() >= 3) {
            String estadoCode = cleaned.substring(1, 3);
            switch (estadoCode) {
                case "01": return "Amazonas";
                case "02": return "Anzoátegui";
                // ... completar con los 23 estados + DC
                case "25": return "Distrito Capital";
                default: return "Desconocido";
            }
        }
        return "Desconocido";
    }
}