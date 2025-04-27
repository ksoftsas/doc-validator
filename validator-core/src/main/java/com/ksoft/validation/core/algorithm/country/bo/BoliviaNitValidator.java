package com.ksoft.validation.core.algorithm.country.bo;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class BoliviaNitValidator implements DocumentValidator {
    
    // Formato: 1-13 dígitos + DV (sin guiones)
    private static final String NIT_PATTERN = "^[0-9]{1,13}[0-9kK]?$";
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber).toUpperCase();
        
        // Validar formato básico
        if (!cleaned.matches(NIT_PATTERN)) {
            return false;
        }
        
        // Separar base y dígito verificador
        String base;
        char dv;
        
        if (cleaned.length() > 1) {
            base = cleaned.substring(0, cleaned.length() - 1);
            dv = cleaned.charAt(cleaned.length() - 1);
        } else {
            // NIT de 1 dígito no tiene DV
            return true;
        }
        
        // Validar que la base sea numérica
        if (!isNumeric(base)) {
            return false;
        }
        
        // Calcular dígito verificador
        char calculatedDv = calculateVerifierDigit(base);
        
        // Comparar con el proporcionado
        return dv == calculatedDv;
    }
    
    private char calculateVerifierDigit(String base) {
        int[] factores = {2, 3, 4, 5, 6, 7};
        int sum = 0;
        
        // Multiplicar cada dígito por el factor correspondiente (de derecha a izquierda)
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(base.length() - 1 - i));
            int factor = factores[i % factores.length];
            sum += digit * factor;
        }
        
        int modulo = 11;
        int residuo = sum % modulo;
        int dvValue = (modulo - residuo) % modulo;
        
        // Caso especial cuando el resultado es 10 (se representa con 'K')
        return (dvValue == 10) ? 'K' : Character.forDigit(dvValue, 10);
    }
    
    public String formatNit(String nit) {
        if (!isValid(nit)) return nit;
        
        String cleaned = cleanNumber(nit).toUpperCase();
        if (cleaned.length() <= 1) return cleaned;
        
        String base = cleaned.substring(0, cleaned.length() - 1);
        char dv = cleaned.charAt(cleaned.length() - 1);
        
        return base + "-" + dv;
    }
}