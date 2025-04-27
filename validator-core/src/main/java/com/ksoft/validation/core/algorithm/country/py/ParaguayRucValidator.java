package com.ksoft.validation.core.algorithm.country.py;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class ParaguayRucValidator implements DocumentValidator {
    
    // Formatos:
    // Persona Física: 8000000-1
    // Persona Jurídica: 80000000-1
    private static final String RUC_PATTERN = "^[0-9]{7,8}-?[0-9]$";
    private static final int[] WEIGHTS_RUC_FISICA = {2, 3, 4, 5, 6, 7, 8};
    private static final int[] WEIGHTS_RUC_JURIDICA = {2, 3, 4, 5, 6, 7, 8, 9};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(RUC_PATTERN)) {
            return false;
        }
        
        // Determinar si es RUC físico (7+1) o jurídico (8+1)
        boolean isFisica = cleaned.length() == 8; // 7 + DV
        boolean isJuridica = cleaned.length() == 9; // 8 + DV
        
        if (!isFisica && !isJuridica) {
            return false;
        }
        
        // Validar dígito verificador
        String base = cleaned.substring(0, cleaned.length() - 1);
        char providedDv = cleaned.charAt(cleaned.length() - 1);
        
        char calculatedDv = isFisica ? 
            calculateFisicaVerifierDigit(base) : 
            calculateJuridicaVerifierDigit(base);
            
        return providedDv == calculatedDv;
    }
    
    private char calculateFisicaVerifierDigit(String base) {
        int sum = 0;
        
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(i));
            sum += digit * WEIGHTS_RUC_FISICA[i];
        }
        
        int remainder = sum % 11;
        int dvValue = 11 - remainder;
        
        // Casos especiales para Paraguay
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
        
        // Casos especiales para Paraguay
        if (dvValue == 11) return '0';
        if (dvValue == 10) return '0';
        
        return Character.forDigit(dvValue, 10);
    }
    
    public String formatRuc(String ruc) {
        if (!isValid(ruc)) return ruc;
        
        String cleaned = cleanNumber(ruc);
        String base = cleaned.substring(0, cleaned.length() - 1);
        String dv = cleaned.substring(cleaned.length() - 1);
        
        return base + "-" + dv;
    }
    
    public String getTipoContribuyente(String ruc) {
        if (!isValid(ruc)) return "Desconocido";
        
        String cleaned = cleanNumber(ruc);
        if (cleaned.length() == 8) {
            return "Persona Física";
        } else {
            char tipo = cleaned.charAt(0);
            switch (tipo) {
                case '1': return "Empresa Privada";
                case '2': return "Empresa Pública";
                case '3': return "Entidad Sin Fines de Lucro";
                case '4': return "Representación Diplomática";
                case '5': return "Persona Física con Actividad Comercial";
                default: return "Otro";
            }
        }
    }
    
    public boolean isRucActivo(String ruc) {
        if (!isValid(ruc)) return false;
        
        // Implementación real requeriría conexión con la SET
        // Esta es una implementación básica para pruebas
        return !ruc.startsWith("999");
    }
}