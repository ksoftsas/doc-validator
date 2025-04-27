package com.ksoft.validation.core.algorithm.country.co;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class ColombiaCcValidator implements DocumentValidator {
    
    // Formato: 5-11 dígitos (dependiendo del año de expedición)
    private static final String CC_PATTERN = "^[0-9]{5,11}$";
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(CC_PATTERN)) {
            return false;
        }
        
        // La cédula colombiana no tiene dígito verificador estándar
        // Validaciones adicionales podrían incluir rangos por departamento
        return true;
    }
    
    public String formatCc(String cc) {
        if (!isValid(cc)) return cc;
        
        // Formato común: separar cada 3 dígitos
        StringBuilder formatted = new StringBuilder();
        String cleaned = cleanNumber(cc);
        
        for (int i = 0; i < cleaned.length(); i++) {
            if (i > 0 && (cleaned.length() - i) % 3 == 0) {
                formatted.append(".");
            }
            formatted.append(cleaned.charAt(i));
        }
        
        return formatted.toString();
    }

    @Override
    public String format(String documentNumber) {
        // Eliminar caracteres no numéricos
        String cleaned = documentNumber.replaceAll("[^0-9]", "");
        
        // Validar CC (8-10 dígitos)
        if (cleaned.matches("\\d{8,10}")) {
            return cleaned;
        }
        
        return documentNumber; // Devolver original si no cumple formato
    }

    @Override
    public String getDocumentType() {
        return "Cédula de Ciudadanía Colombiana (CC)";
    }
}