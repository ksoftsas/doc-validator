package com.ksoft.validation.core.algorithm.country.cl;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class ChileRutValidator implements DocumentValidator {
    
    // Formato: 7-8 dígitos + guión + DV (K o dígito)
    private static final String RUT_PATTERN = "^[0-9]{7,8}-[0-9kK]$";
    private static final int[] WEIGHTS = {2, 3, 4, 5, 6, 7, 2, 3};
    
    @Override
    public boolean isValid(String documentNumber) {
        // Limpiar y estandarizar el formato
        String cleaned = cleanFormat(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(RUT_PATTERN)) {
            return false;
        }
        
        // Separar número y dígito verificador
        String[] parts = cleaned.split("-");
        String numberPart = parts[0];
        char dv = parts[1].toUpperCase().charAt(0);
        
        // Calcular dígito verificador
        char calculatedDv = calculateVerifierDigit(numberPart);
        
        // Comparar dígitos verificadores
        return dv == calculatedDv;
    }
    
    private String cleanFormat(String rut) {
        // Eliminar puntos y normalizar
        return rut.replaceAll("[^0-9kK-]", "").toUpperCase();
    }
    
    private char calculateVerifierDigit(String numberPart) {
        int sum = 0;
        int weightIndex = 0;
        
        // Multiplicar cada dígito por su peso (de derecha a izquierda)
        for (int i = numberPart.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(numberPart.charAt(i));
            sum += digit * WEIGHTS[weightIndex % WEIGHTS.length];
            weightIndex++;
        }
        
        int remainder = sum % 11;
        int dvValue = 11 - remainder;
        
        // Manejar casos especiales
        if (dvValue == 11) {
            return '0';
        } else if (dvValue == 10) {
            return 'K';
        } else {
            return Character.forDigit(dvValue, 10);
        }
    }
    
    public String formatRut(String rut) {
        if (!isValid(rut)) return rut;
        
        String cleaned = cleanFormat(rut);
        String[] parts = cleaned.split("-");
        
        // Formatear con puntos separadores de miles
        String numberPart = parts[0];
        StringBuilder formatted = new StringBuilder();
        
        int len = numberPart.length();
        for (int i = 0; i < len; i++) {
            if (i > 0 && (len - i) % 3 == 0) {
                formatted.append(".");
            }
            formatted.append(numberPart.charAt(i));
        }
        
        return formatted.append("-").append(parts[1]).toString();
    }
}