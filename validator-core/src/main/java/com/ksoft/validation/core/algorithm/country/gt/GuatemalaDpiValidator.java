package com.ksoft.validation.core.algorithm.country.gt;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class GuatemalaDpiValidator implements DocumentValidator {
    
    // Formato: 13 dígitos (4 campos + dígito verificador)
    private static final String DPI_PATTERN = "^[0-9]{13}$";
    private static final int[] WEIGHTS = {2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(DPI_PATTERN)) {
            return false;
        }
        
        // Validar dígito verificador con módulo 11
        String base = cleaned.substring(0, 12);
        char providedDv = cleaned.charAt(12);
        
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
        if (dvValue == 10) return '1'; // En Guatemala, si da 10 se usa 1
        
        return Character.forDigit(dvValue, 10);
    }
    
    public String formatDpi(String dpi) {
        if (!isValid(dpi)) return dpi;
        
        // Formato: CUI-AAAA MMNNN CCCC
        return String.format("%s-%s %s-%s",
            dpi.substring(0, 4),
            dpi.substring(4, 8),
            dpi.substring(8, 11),
            dpi.substring(11));
    }
    
    public String getDepartamento(String dpi) {
        if (!isValid(dpi)) return "Desconocido";
        
        String codigoDepto = dpi.substring(9, 11);
        switch (codigoDepto) {
            case "01": return "Guatemala";
            case "02": return "El Progreso";
            case "03": return "Sacatepéquez";
            // ... completar con los 22 departamentos
            case "22": return "Petén";
            default: return "Desconocido";
        }
    }

    @Override
    public String format(String documentNumber) {
        // Eliminar caracteres no numéricos
        String cleaned = documentNumber.replaceAll("[^0-9]", "");
        
        // Validar DPI_GT (13 dígitos)
        if (cleaned.matches("\\d{13}")) {
            return cleaned;
        }
        
        return documentNumber; // Devolver original si no cumple formato
    }

    @Override
    public String getDocumentType() {
        return "Documento Personal de Identificación Guatemalteco (DPI)";
    }
}