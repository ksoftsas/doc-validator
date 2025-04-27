package com.ksoft.validation.core.algorithm.country.ar;

import com.ksoft.validation.core.algorithm.DocumentValidator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArgentinaCuitCuilValidator implements DocumentValidator {
    
    // Pesos para cálculo del dígito verificador
    private static final int[] WEIGHTS = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
    
    // Prefijos válidos para CUIT/CUIL
    private static final Set<String> VALID_PREFIXES = new HashSet<>(Arrays.asList(
        "20", "23", "24", "27", "30", "33", "34"
    ));
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validación básica
        if (cleaned.length() != 11 || !isNumeric(cleaned)) {
            return false;
        }
        
        // Validar prefijo
        String prefix = cleaned.substring(0, 2);
        if (!VALID_PREFIXES.contains(prefix)) {
            return false;
        }
        
        // Calcular dígito verificador
        int calculatedDv = calculateVerifierDigit(cleaned.substring(0, 10));
        int providedDv = Character.getNumericValue(cleaned.charAt(10));
        
        // Comparar dígitos verificadores
        return calculatedDv == providedDv;
    }
    
    private int calculateVerifierDigit(String base) {
        int sum = 0;
        
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(i));
            sum += digit * WEIGHTS[i];
        }
        
        int remainder = sum % 11;
        int dv = 11 - remainder;
        
        // Casos especiales
        if (dv == 11) {
            dv = 0;
        } else if (dv == 10) {
            // Manejo especial para cuando el resultado es 10
            // Depende del prefijo:
            // - Para prefijo 23 puede ser 9 o 4
            // - Para otros prefijos generalmente es 9
            dv = (base.startsWith("23")) ? 4 : 9; // Simplificación, según reglas AFIP
        }
        
        return dv;
    }
}