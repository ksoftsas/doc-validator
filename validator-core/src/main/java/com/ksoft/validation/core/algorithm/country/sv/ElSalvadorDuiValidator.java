package com.ksoft.validation.core.algorithm.country.sv;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class ElSalvadorDuiValidator implements DocumentValidator {
    
    // Formato: 8 dígitos + guión + 1 dígito verificador
    private static final String DUI_PATTERN = "^[0-9]{8}-[0-9]$";
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico (8 dígitos + 1 DV)
        if (!cleaned.matches("^[0-9]{9}$")) {
            return false;
        }
        
        // Extraer número y dígito verificador
        String base = cleaned.substring(0, 8);
        int providedDv = Character.getNumericValue(cleaned.charAt(8));
        
        // Calcular dígito verificador con módulo 10
        int calculatedDv = calculateModulo10(base);
        
        return providedDv == calculatedDv;
    }
    
    private int calculateModulo10(String base) {
        int sum = 0;
        int[] weights = {9, 8, 7, 6, 5, 4, 3, 2}; // Pesos para cada dígito
        
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(i));
            sum += digit * weights[i];
        }
        
        int remainder = sum % 10;
        return (remainder == 0) ? 0 : (10 - remainder);
    }
    
    public String formatDui(String dui) {
        if (!isValid(dui)) return dui;
        
        String cleaned = cleanNumber(dui);
        return cleaned.substring(0, 8) + "-" + cleaned.charAt(8);
    }
    
    public String getDepartamento(String dui) {
        if (!isValid(dui)) return "Desconocido";
        
        String cleaned = cleanNumber(dui);
        int primerDigito = Character.getNumericValue(cleaned.charAt(0));
        
        switch (primerDigito) {
            case 0: return "Ahuachapán";
            case 1: return "Santa Ana";
            case 2: return "Sonsonate";
            // ... completar con otros departamentos
            case 8: return "San Miguel";
            case 9: return "Extranjero";
            default: return "Desconocido";
        }
    }

    @Override
    public String format(String documentNumber) {
        // Eliminar caracteres no numéricos ni guiones
        String cleaned = documentNumber.replaceAll("[^0-9-]", "");
        
        // Validar DUI_SV (formato: XXXXXXXX-X)
        if (cleaned.matches("\\d{8}-\\d{1}")) {
            return cleaned;
        }
        
        return documentNumber; // Devolver original si no cumple formato
    }

    @Override
    public String getDocumentType() {
        return "Documento Único de Identidad Salvadoreño (DUI)";
    }
}