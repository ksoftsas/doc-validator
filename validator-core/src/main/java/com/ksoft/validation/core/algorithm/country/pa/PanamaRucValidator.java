package com.ksoft.validation.core.algorithm.country.pa;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class PanamaRucValidator implements DocumentValidator {
    
    // Formatos:
    // Persona Natural: 10-12345-67-8
    // Persona Jurídica: 123456789-1-23
    private static final String RUC_NATURAL_PATTERN = "^[0-9]{2}-?[0-9]{4,5}-?[0-9]{2}-?[0-9]$";
    private static final String RUC_JURIDICA_PATTERN = "^[0-9]{9}-?[0-9]-?[0-9]{2}$";
    private static final int[] WEIGHTS_RUC_NATURAL = {7, 6, 5, 4, 3, 2, 1, 7, 6, 5, 4, 3, 2};
    private static final int[] WEIGHTS_RUC_JURIDICA = {4, 3, 2, 7, 6, 5, 4, 3, 2};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Determinar si es RUC natural o jurídica
        boolean isNatural = cleaned.length() == 10;
        boolean isJuridica = cleaned.length() == 12;
        
        if (!isNatural && !isJuridica) {
            return false;
        }
        
        if (isNatural) {
            return validateNaturalRuc(cleaned);
        } else {
            return validateJuridicaRuc(cleaned);
        }
    }
    
    private boolean validateNaturalRuc(String ruc) {
        // Validar formato
        if (!ruc.matches(RUC_NATURAL_PATTERN)) {
            return false;
        }
        
        String base = ruc.substring(0, 9);
        char providedDv = ruc.charAt(9);
        
        char calculatedDv = calculateNaturalVerifierDigit(base);
        return providedDv == calculatedDv;
    }
    
    private boolean validateJuridicaRuc(String ruc) {
        // Validar formato
        if (!ruc.matches(RUC_JURIDICA_PATTERN)) {
            return false;
        }
        
        String base = ruc.substring(0, 9);
        char providedDv = ruc.charAt(9);
        
        char calculatedDv = calculateJuridicaVerifierDigit(base);
        return providedDv == calculatedDv;
    }
    
    private char calculateNaturalVerifierDigit(String base) {
        int sum = 0;
        
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(i));
            sum += digit * WEIGHTS_RUC_NATURAL[i];
        }
        
        int remainder = sum % 11;
        int dvValue = 11 - remainder;
        
        // Casos especiales para Panamá
        if (dvValue == 11) return '0';
        if (dvValue == 10) return '0';
        
        return Character.forDigit(dvValue, 10);
    }
    
    private char calculateJuridicaVerifierDigit(String base) {
        int sum = 0;
        
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(i));
            sum += digit * WEIGHTS_RUC_JURIDICA[i];
        }
        
        int remainder = sum % 11;
        int dvValue = 11 - remainder;
        
        // Casos especiales para Panamá
        if (dvValue == 11) return '0';
        if (dvValue == 10) return '0';
        
        return Character.forDigit(dvValue, 10);
    }
    
    public String formatRuc(String ruc) {
        if (!isValid(ruc)) return ruc;
        
        String cleaned = cleanNumber(ruc);
        
        if (cleaned.length() == 10) { // Natural
            return String.format("%s-%s-%s-%s",
                cleaned.substring(0, 2),
                cleaned.substring(2, 7),
                cleaned.substring(7, 9),
                cleaned.substring(9));
        } else { // Jurídica
            return String.format("%s-%s-%s",
                cleaned.substring(0, 9),
                cleaned.substring(9, 10),
                cleaned.substring(10));
        }
    }
    
    public String getTipoContribuyente(String ruc) {
        if (!isValid(ruc)) return "Desconocido";
        
        String cleaned = cleanNumber(ruc);
        if (cleaned.length() == 10) {
            return "Persona Natural";
        } else {
            char tipo = cleaned.charAt(9);
            switch (tipo) {
                case '1': return "Persona Jurídica";
                case '2': return "Empresa Extranjera";
                case '3': return "Entidad Pública";
                case '4': return "Agente Retenedor";
                default: return "Otro";
            }
        }
    }
}