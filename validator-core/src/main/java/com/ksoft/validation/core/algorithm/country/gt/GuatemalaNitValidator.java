package com.ksoft.validation.core.algorithm.country.gt;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class GuatemalaNitValidator implements DocumentValidator {
    
    // Formato: 8 dígitos + guión + 1 DV (opcional) o 9 dígitos continuos
    private static final String NIT_PATTERN = "^[0-9]{8}-?[0-9]$";
    private static final int[] WEIGHTS = {3, 2, 7, 6, 5, 4, 3, 2};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico (8 dígitos + 1 DV)
        if (cleaned.length() != 9) {
            return false;
        }
        
        // Separar base y dígito verificador
        String base = cleaned.substring(0, 8);
        char providedDv = cleaned.charAt(8);
        
        // Calcular dígito verificador
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
        
        // Casos especiales
        if (dvValue == 11) return '0';
        if (dvValue == 10) {
            // En Guatemala, si el cálculo da 10, se usa 'K'
            return 'K';
        }
        
        return Character.forDigit(dvValue, 10);
    }
    
    public String formatNit(String nit) {
        if (!isValid(nit)) return nit;
        
        String cleaned = cleanNumber(nit);
        return String.format("%s-%s",
            cleaned.substring(0, 8),
            cleaned.substring(8));
    }
    
    public String getTipoContribuyente(String nit) {
        if (!isValid(nit)) return "Desconocido";
        
        char primerDigito = nit.charAt(0);
        switch (primerDigito) {
            case '1': return "Persona individual";
            case '2': return "Persona jurídica";
            case '3': return "Entidades públicas";
            case '4': return "Extranjeros sin DPI";
            case '5': return "Regímenes especiales";
            default: return "Otro";
        }
    }
}