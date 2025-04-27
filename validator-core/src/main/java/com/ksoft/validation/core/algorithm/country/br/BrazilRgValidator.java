package com.ksoft.validation.core.algorithm.country.br;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class BrazilRgValidator implements DocumentValidator {
    
    // Formato: 2-9 dígitos + DV (opcional, puede ser X para 10)
    private static final String RG_PATTERN = "^[0-9]{1,9}[0-9Xx]?$";
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber).toUpperCase();
        
        // Validar formato básico
        if (!cleaned.matches(RG_PATTERN)) {
            return false;
        }
        
        // Separar número y DV (si existe)
        String base;
        char dv;
        
        if (cleaned.length() > 1) {
            base = cleaned.substring(0, cleaned.length() - 1);
            dv = cleaned.charAt(cleaned.length() - 1);
        } else {
            // RG sin DV es válido
            return true;
        }
        
        // Calcular dígito verificador
        char calculatedDv = calculateVerifierDigit(base);
        
        // Comparar con el proporcionado
        return dv == calculatedDv;
    }
    
    private char calculateVerifierDigit(String base) {
        int sum = 0;
        int weight = 2;
        
        // Multiplicar cada dígito por peso (2 a 9)
        for (int i = base.length() - 1; i >= 0; i--) {
            sum += (base.charAt(i) - '0') * weight;
            weight++;
        }
        
        int remainder = sum % 11;
        int dvValue = 11 - remainder;
        
        // Caso especial cuando el resultado es 10 (se representa con 'X')
        return (dvValue == 10) ? 'X' : Character.forDigit(dvValue, 10);
    }
    
    public String formatRg(String rg) {
        if (!isValid(rg)) return rg;
        
        String cleaned = cleanNumber(rg).toUpperCase();
        if (cleaned.length() <= 1) return cleaned;
        
        String base = cleaned.substring(0, cleaned.length() - 1);
        char dv = cleaned.charAt(cleaned.length() - 1);
        
        return base + "-" + dv;
    }

    @Override
    public String format(String documentNumber) {
        // Eliminar caracteres no alfanuméricos
        String cleaned = documentNumber.replaceAll("[^A-Za-z0-9.-]", "").toUpperCase();
        
        // Validar RG (formato flexible: XX.XXX.XXX-X)
        if (cleaned.matches("[A-Z]{2}\\d{2,3}\\.\\d{3}\\.\\d{3}-[A-Z0-9]")) {
            return cleaned;
        }
        
        return documentNumber; // Devolver original si no cumple formato
    }

    @Override
    public String getDocumentType() {
        return "Registro Geral (RG)";
    }
}