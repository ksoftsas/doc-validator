package com.ksoft.validation.core.algorithm.country.uy;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class UruguayCiValidator implements DocumentValidator {
    
    // Formato: 6-8 dígitos + dígito verificador (total 7-9 caracteres)
    private static final String CI_PATTERN = "^[0-9]{6,8}-?[0-9]$";
    private static final int[] WEIGHTS = {2, 9, 8, 7, 6, 3, 4};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar longitud básica (7-9 dígitos)
        if (cleaned.length() < 7 || cleaned.length() > 9) {
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
        
        int remainder = sum % 10;
        int dvValue = (10 - remainder) % 10;
        
        return Character.forDigit(dvValue, 10);
    }
    
    public String formatCi(String ci) {
        if (!isValid(ci)) return ci;
        
        String cleaned = cleanNumber(ci);
        String base = cleaned.substring(0, cleaned.length() - 1);
        String dv = cleaned.substring(cleaned.length() - 1);
        
        return base + "." + dv;
    }
    
    public String getDepartamento(String ci) {
        if (!isValid(ci)) return "Desconocido";
        
        String cleaned = cleanNumber(ci);
        // Los primeros dígitos pueden indicar departamento
        if (cleaned.length() >= 3) {
            String deptoCode = cleaned.substring(0, 2);
            switch (deptoCode) {
                case "01": return "Montevideo";
                case "02": return "Artigas";
                case "03": return "Canelones";
                // ... completar con los 19 departamentos
                case "19": return "Treinta y Tres";
                default: return "Desconocido";
            }
        }
        return "Desconocido";
    }
    
    public boolean isCiExtranjera(String ci) {
        if (!isValid(ci)) return false;
        // CIs de extranjeros tienen ciertos patrones
        String cleaned = cleanNumber(ci);
        return cleaned.startsWith("5") || cleaned.startsWith("6");
    }

    @Override
    public String format(String documentNumber) {
        // Eliminar caracteres no numéricos
        String cleaned = documentNumber.replaceAll("[^0-9]", "");
        
        // Validar CI_UY (7-8 dígitos)
        if (cleaned.matches("\\d{7,8}")) {
            return cleaned;
        }
        
        return documentNumber; // Devolver original si no cumple formato
    }

    @Override
    public String getDocumentType() {
        return "Cédula de Identidad Uruguaya (CI)";
    }
}