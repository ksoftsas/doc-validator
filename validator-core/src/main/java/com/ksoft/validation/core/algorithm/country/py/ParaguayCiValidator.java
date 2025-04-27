package com.ksoft.validation.core.algorithm.country.py;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class ParaguayCiValidator implements DocumentValidator {
    
    // Formato: 1-8 dígitos + dígito verificador (total 6-9 caracteres)
    private static final String CI_PATTERN = "^[0-9]{1,8}-?[0-9]$";
    private static final int[] WEIGHTS = {2, 3, 4, 5, 6, 7, 8, 9};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar longitud básica (2-9 dígitos)
        if (cleaned.length() < 2 || cleaned.length() > 9) {
            return false;
        }
        
        // Validar formato
        if (!cleaned.matches(CI_PATTERN)) {
            return false;
        }
        
        // Separar base y dígito verificador
        String base = cleaned.substring(0, cleaned.length() - 1);
        char providedDv = cleaned.charAt(cleaned.length() - 1);
        
        // Calcular dígito verificador
        char calculatedDv = calculateVerifierDigit(base);
        
        return providedDv == calculatedDv;
    }
    
    private char calculateVerifierDigit(String base) {
        int sum = 0;
        
        // Aplicar pesos de derecha a izquierda
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(base.length() - 1 - i));
            int weight = WEIGHTS[i % WEIGHTS.length];
            sum += digit * weight;
        }
        
        int remainder = sum % 11;
        int dvValue = 11 - remainder;
        
        // Casos especiales para Paraguay
        if (dvValue == 11) return '0';
        if (dvValue == 10) return '0';
        
        return Character.forDigit(dvValue, 10);
    }
    
    public String formatCi(String ci) {
        if (!isValid(ci)) return ci;
        
        String cleaned = cleanNumber(ci);
        String base = cleaned.substring(0, cleaned.length() - 1);
        String dv = cleaned.substring(cleaned.length() - 1);
        
        return base + "-" + dv;
    }
    
    public String getDepartamento(String ci) {
        if (!isValid(ci)) return "Desconocido";
        
        String cleaned = cleanNumber(ci);
        // Los primeros dígitos pueden indicar departamento
        if (cleaned.length() >= 3) {
            String deptoCode = cleaned.substring(0, 2);
            switch (deptoCode) {
                case "00": return "Asunción";
                case "01": return "Concepción";
                case "02": return "San Pedro";
                // ... completar con los 17 departamentos
                case "17": return "Alto Paraguay";
                default: return "Desconocido";
            }
        }
        return "Desconocido";
    }

    @Override
    public String format(String documentNumber) {
        // Eliminar caracteres no numéricos
        String cleaned = documentNumber.replaceAll("[^0-9]", "");
        
        // Validar CI_PY (6-8 dígitos)
        if (cleaned.matches("\\d{6,8}")) {
            return cleaned;
        }
        
        return documentNumber; // Devolver original si no cumple formato
    }

    @Override
    public String getDocumentType() {
        return "Cédula de Identidad Paraguaya (CI)";
    }
}