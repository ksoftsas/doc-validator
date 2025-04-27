package com.ksoft.validation.core.algorithm.mod11;

import com.ksoft.validation.core.algorithm.DocumentValidator;
import java.util.Arrays;

public class Mod11Validator implements DocumentValidator {
    protected final int[] weights;
    protected final boolean reverseOrder;
    protected final int expectedLength;
    protected final boolean allowK;
    protected final boolean strictLength;

    public Mod11Validator(int[] weights, boolean reverseOrder, int expectedLength, boolean allowK) {
        this.weights = weights;
        this.reverseOrder = reverseOrder;
        this.expectedLength = expectedLength;
        this.allowK = allowK;
        this.strictLength = expectedLength > 0;
    }

    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validaciones básicas
        if (cleaned.isEmpty()) {
            return false;
        }
        
        // Validar longitud si es estricto
        if (strictLength && cleaned.length() != expectedLength) {
            return false;
        }
        
        // Separar dígito verificador del resto
        String base = cleaned.substring(0, cleaned.length() - 1);
        char dvChar = cleaned.charAt(cleaned.length() - 1);
        
        // Validar que la base sea numérica
        if (!isNumeric(base)) {
            return false;
        }
        
        // Validar dígito verificador
        if (!Character.isDigit(dvChar)) {
            if (!(allowK && (dvChar == 'K' || dvChar == 'k'))) {
                return false;
            }
        }
        
        // Calcular dígito verificador esperado
        char calculatedDv = calculateVerifierDigit(base);
        
        // Comparar con el proporcionado
        return Character.toUpperCase(dvChar) == calculatedDv;
    }

    protected char calculateVerifierDigit(String base) {
        int sum = 0;
        int weightIndex = 0;
        
        // Aplicar pesos según el orden especificado
        for (int i = reverseOrder ? base.length() - 1 : 0; 
             reverseOrder ? i >= 0 : i < base.length(); 
             i += reverseOrder ? -1 : 1) {
            
            int digit = Character.getNumericValue(base.charAt(i));
            int weight = weights[weightIndex % weights.length];
            
            sum += digit * weight;
            weightIndex++;
        }
        
        int remainder = sum % 11;
        int dvValue = 11 - remainder;
        
        // Manejar casos especiales
        if (dvValue == 11) {
            return '0';
        } else if (dvValue == 10) {
            return 'K'; // Representación estándar para 10
        } else {
            return Character.forDigit(dvValue, 10);
        }
    }

    @Override
    public String toString() {
        return "Mod11Validator{" +
                "weights=" + Arrays.toString(weights) +
                ", reverseOrder=" + reverseOrder +
                ", expectedLength=" + expectedLength +
                ", allowK=" + allowK +
                ", strictLength=" + strictLength +
                '}';
    }

    @Override
    public String format(String documentNumber) {
        return null;
    }

    @Override
    public String getDocumentType() {
        return null;
    }
}