package com.ksoft.validation.core.algorithm.country.dr;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class DominicanRncValidator implements DocumentValidator {
    
    // Formatos:
    // Persona Física: 9 dígitos (incluyendo DV)
    // Persona Jurídica: 9 dígitos (incluyendo DV)
    private static final String RNC_PATTERN = "^[0-9]{9}$";
    private static final int[] WEIGHTS = {7, 9, 8, 6, 5, 4, 3, 2};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(RNC_PATTERN)) {
            return false;
        }
        
        // Validar dígito verificador con módulo 11
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
        
        // Casos especiales para RNC
        if (dvValue == 11) return '1';
        if (dvValue == 10) return '0';
        
        return Character.forDigit(dvValue, 10);
    }
    
    public String formatRnc(String rnc) {
        if (!isValid(rnc)) return rnc;
        
        return String.format("%s-%s",
            rnc.substring(0, 8),
            rnc.substring(8));
    }
    
    public String getTipoContribuyente(String rnc) {
        if (!isValid(rnc)) return "Desconocido";
        
        char tipo = rnc.charAt(0);
        switch (tipo) {
            case '1': return "Persona Física";
            case '2': return "Persona Jurídica";
            case '3': return "Agente de Retención";
            case '4': return "Exportador";
            case '5': return "Gobierno";
            default: return "Otro";
        }
    }
    
    public boolean isRncActivo(String rnc) {
        if (!isValid(rnc)) return false;
        // Implementación real requeriría conexión con DGII
        return !rnc.startsWith("999");
    }
}