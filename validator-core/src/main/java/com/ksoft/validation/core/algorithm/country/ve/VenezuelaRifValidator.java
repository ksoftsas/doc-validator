package com.ksoft.validation.core.algorithm.country.ve;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class VenezuelaRifValidator implements DocumentValidator {
    
    // Formatos:
    // J-12345678-9 (Persona Jurídica)
    // V-12345678-9 (Persona Natural)
    // E-12345678-9 (Extranjero)
    // G-12345678-9 (Gobierno)
    private static final String RIF_PATTERN = "^[JVEGjveg]-?[0-9]{8}-?[0-9]$";
    private static final int[] WEIGHTS = {4, 3, 2, 7, 6, 5, 4, 3, 2};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanRifNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(RIF_PATTERN)) {
            return false;
        }
        
        // Extraer partes
        char type = cleaned.charAt(0);
        String numbers = cleaned.substring(1, 9);
        char providedDv = cleaned.charAt(9);
        
        // Calcular dígito verificador
        char calculatedDv = calculateVerifierDigit(type, numbers);
        return providedDv == calculatedDv;
    }
    
    private String cleanRifNumber(String rif) {
        if (rif == null) return "";
        // Eliminar espacios y guiones, convertir a mayúsculas
        return rif.replaceAll("[^JVEGjveg0-9]", "").toUpperCase();
    }
    
    private char calculateVerifierDigit(char type, String numbers) {
        int sum = 0;
        
        // Calcular valor inicial según tipo de RIF
        int typeValue = getTypeValue(type);
        sum += typeValue * WEIGHTS[0];
        
        // Sumar dígitos con sus pesos
        for (int i = 0; i < numbers.length(); i++) {
            int digit = Character.getNumericValue(numbers.charAt(i));
            sum += digit * WEIGHTS[i+1];
        }
        
        int remainder = sum % 11;
        int dvValue = 11 - remainder;
        
        // Casos especiales para Venezuela
        if (dvValue == 11) return '0';
        if (dvValue == 10) return '0';
        
        return Character.forDigit(dvValue, 10);
    }
    
    private int getTypeValue(char type) {
        switch (Character.toUpperCase(type)) {
            case 'J': return 0; // Jurídico
            case 'V': return 1; // Natural
            case 'E': return 2; // Extranjero
            case 'G': return 3; // Gobierno
            default: return 0;
        }
    }
    
    public String formatRif(String rif) {
        String cleaned = cleanRifNumber(rif);
        
        if (!isValid(cleaned)) {
            return rif;
        }
        
        return String.format("%s-%s-%s",
            cleaned.substring(0, 1),
            cleaned.substring(1, 9),
            cleaned.substring(9));
    }
    
    public String getTipoContribuyente(String rif) {
        String cleaned = cleanRifNumber(rif);
        
        if (!isValid(cleaned)) {
            return "Desconocido";
        }
        
        char type = cleaned.charAt(0);
        switch (type) {
            case 'J': return "Persona Jurídica";
            case 'V': return "Persona Natural";
            case 'E': return "Extranjero";
            case 'G': return "Gobierno";
            default: return "Desconocido";
        }
    }
    
    public boolean isRifActivo(String rif) {
        if (!isValid(rif)) return false;
        // Implementación real requeriría conexión con SENIAT
        return !rif.startsWith("999", 1); // Verificar si los números comienzan con 999
    }

    @Override
    public String format(String documentNumber) {
        String cleaned = documentNumber.replaceAll("[^JVE0-9]", "").toUpperCase();
        
        if(cleaned.matches("^[JVE]\\d{8}\\d$")) {
            return cleaned.charAt(0) + "-" + 
                   cleaned.substring(1, 9) + "-" + 
                   cleaned.substring(9);
        }
        
        return documentNumber;
    }
    
    @Override
    public String getDocumentType() {
        return "RIF Venezolano";
    }
}