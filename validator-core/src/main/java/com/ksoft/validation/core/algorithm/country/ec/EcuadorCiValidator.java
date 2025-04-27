package com.ksoft.validation.core.algorithm.country.ec;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class EcuadorCiValidator implements DocumentValidator {
    
    private static final String CI_PATTERN = "^[0-9]{10}$";
    private static final int[] PROVINCIAS_VALIDAS = {
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 
        16, 17, 18, 19, 20, 21, 22, 23, 24, 30 // 30 para extranjeros
    };
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico (10 dígitos)
        if (!cleaned.matches(CI_PATTERN)) {
            return false;
        }
        
        // Validar provincia (primeros dos dígitos)
        int provincia;
        try {
            provincia = Integer.parseInt(cleaned.substring(0, 2));
        } catch (NumberFormatException e) {
            return false;
        }
        
        if (!isProvinciaValida(provincia)) {
            return false;
        }
        
        // Validar tercer dígito (0-5 para personas naturales, 6 para empresas públicas)
        int tercerDigito = Character.getNumericValue(cleaned.charAt(2));
        if (tercerDigito < 0 || tercerDigito > 6) {
            return false;
        }
        
        // Aplicar algoritmo módulo 10 (Luhn)
        return validateModulo10(cleaned);
    }
    
    private boolean isProvinciaValida(int provincia) {
        for (int p : PROVINCIAS_VALIDAS) {
            if (p == provincia) {
                return true;
            }
        }
        return false;
    }
    
    private boolean validateModulo10(String ci) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(ci.charAt(i));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        
        int calculatedDv = (sum % 10 == 0) ? 0 : (10 - (sum % 10));
        int providedDv = Character.getNumericValue(ci.charAt(9));
        
        return calculatedDv == providedDv;
    }
    
    public String getProvincia(String ci) {
        if (!isValid(ci)) return "Desconocida";
        
        int codigo = Integer.parseInt(ci.substring(0, 2));
        switch (codigo) {
            case 1: return "Azuay";
            case 2: return "Bolívar";
            case 3: return "Cañar";
            // ... completar con otras provincias
            case 30: return "Extranjero";
            default: return "Desconocida";
        }
    }

    @Override
    public String format(String documentNumber) {
        // Eliminar caracteres no numéricos
        String cleaned = documentNumber.replaceAll("[^0-9]", "");
        
        // Validar CI_EC (10 dígitos)
        if (cleaned.matches("\\d{10}")) {
            return cleaned;
        }
        
        return documentNumber; // Devolver original si no cumple formato
    }

    @Override
    public String getDocumentType() {
        return "Cédula de Identidad Ecuatoriana (CI)";
    }
}