package com.ksoft.validation.core.algorithm.country.pe;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class PeruRucValidator implements DocumentValidator {
    
    // Formatos:
    // Persona Natural: 10XXXXXXX-X
    // Persona Jurídica: 20XXXXXXX-X
    private static final String RUC_PATTERN = "^[12]0[0-9]{9}$";
    private static final int[] WEIGHTS_NATURAL = {3, 2, 7, 6, 5, 4, 3, 2};
    private static final int[] WEIGHTS_JURIDICA = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(RUC_PATTERN)) {
            return false;
        }
        
        // Determinar tipo de RUC (10: natural, 20: jurídica)
        boolean isNatural = cleaned.startsWith("10");
        boolean isJuridica = cleaned.startsWith("20");
        
        if (!isNatural && !isJuridica) {
            return false;
        }
        
        // Validar dígito verificador
        String base = cleaned.substring(0, cleaned.length() - 1);
        char providedDv = cleaned.charAt(cleaned.length() - 1);
        
        char calculatedDv = isNatural ? 
            calculateNaturalVerifierDigit(base) : 
            calculateJuridicaVerifierDigit(base);
            
        return providedDv == calculatedDv;
    }
    
    private char calculateNaturalVerifierDigit(String base) {
        // Para RUC natural (10XXXXXXX), usar solo los primeros 8 dígitos (DNI)
        String dniPart = base.substring(2, 10);
        int sum = 0;
        
        for (int i = 0; i < dniPart.length(); i++) {
            int digit = Character.getNumericValue(dniPart.charAt(i));
            sum += digit * WEIGHTS_NATURAL[i];
        }
        
        int remainder = sum % 11;
        int dvValue = 11 - remainder;
        
        // Casos especiales para Perú
        if (dvValue == 10) return 'K';
        if (dvValue == 11) return '0';
        
        return Character.forDigit(dvValue, 10);
    }
    
    private char calculateJuridicaVerifierDigit(String base) {
        int sum = 0;
        
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(i));
            sum += digit * WEIGHTS_JURIDICA[i];
        }
        
        int remainder = sum % 11;
        int dvValue = 11 - remainder;
        
        // Casos especiales para Perú
        if (dvValue == 10) return 'K';
        if (dvValue == 11) return '0';
        
        return Character.forDigit(dvValue, 10);
    }
    
    public String formatRuc(String ruc) {
        if (!isValid(ruc)) return ruc;
        
        return ruc.substring(0, 11) + "-" + ruc.charAt(11);
    }
    
    public String getTipoContribuyente(String ruc) {
        if (!isValid(ruc)) return "Desconocido";
        
        if (ruc.startsWith("10")) {
            return "Persona Natural";
        } else if (ruc.startsWith("20")) {
            return "Persona Jurídica";
        } else {
            return "Otro";
        }
    }
    
    public String getDniFromRucNatural(String ruc) {
        if (!isValid(ruc) || !ruc.startsWith("10")) return null;
        return ruc.substring(2, 10);
    }
}