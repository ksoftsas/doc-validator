package com.ksoft.validation.core.algorithm.mod10;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class Mod10Validator implements DocumentValidator {
    private final int[] weights;
    private final boolean reverse;
    private final int expectedLength;
    private final String prefixPattern;

    public Mod10Validator() {
        this(new int[]{2, 1}, true, -1, null);
    }

    public Mod10Validator(int[] weights, boolean reverse, int expectedLength, String prefixPattern) {
        this.weights = weights;
        this.reverse = reverse;
        this.expectedLength = expectedLength;
        this.prefixPattern = prefixPattern;
    }

    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validaciones básicas
        if (cleaned.isEmpty() || !isNumeric(cleaned)) {
            return false;
        }
        
        // Validar longitud si se especificó
        if (expectedLength > 0 && cleaned.length() != expectedLength) {
            return false;
        }
        
        // Validar prefijo si se especificó
        if (prefixPattern != null && !cleaned.matches(prefixPattern)) {
            return false;
        }
        
        // Aplicar algoritmo Mod10
        int sum = 0;
        int weightIndex = 0;
        
        // Iterar en el orden especificado
        for (int i = reverse ? cleaned.length() - 1 : 0; 
             reverse ? i >= 0 : i < cleaned.length(); 
             i += reverse ? -1 : 1) {
            
            int digit = Character.getNumericValue(cleaned.charAt(i));
            int weight = weights[weightIndex % weights.length];
            
            int product = digit * weight;
            sum += (product > 9) ? (product - 9) : product; // Equivalente a sumar los dígitos
            
            weightIndex++;
        }
        
        return (sum % 10) == 0;
    }
}