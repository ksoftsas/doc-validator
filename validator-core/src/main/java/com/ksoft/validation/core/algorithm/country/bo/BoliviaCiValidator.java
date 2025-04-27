package com.ksoft.validation.core.algorithm.country.bo;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class BoliviaCiValidator implements DocumentValidator {
    
    // Formato: 1-7 dígitos principales + 1-2 letras de expedición (opcional)
    private static final String CI_PATTERN = "^[0-9]{1,7}[A-Za-z]{0,2}$";
    
    // Departamentos bolivianos por código inicial
    private static final String[] DEPARTAMENTOS = {
        "LP", // 0: La Paz
        "CB", // 1: Cochabamba
        "SC", // 2: Santa Cruz
        "OR", // 3: Oruro
        "PT", // 4: Potosí
        "TJ", // 5: Tarija
        "CH", // 6: Chuquisaca
        "BN", // 7: Beni
        "PA"  // 8: Pando
    };
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber).toUpperCase();
        
        // Separar parte numérica y alfabética
        String numericPart = cleaned.replaceAll("[^0-9]", "");
        String alphaPart = cleaned.replaceAll("[^A-Z]", "");
        
        // Validar formato básico
        if (!cleaned.matches(CI_PATTERN)) {
            return false;
        }
        
        // Validar longitud de parte numérica (1-7 dígitos)
        if (numericPart.length() < 1 || numericPart.length() > 7) {
            return false;
        }
        
        // Validar letras de expedición (si existen)
        if (!alphaPart.isEmpty() && alphaPart.length() > 2) {
            return false;
        }
        
        // Validar departamento (primer dígito)
        try {
            int depCode = Integer.parseInt(numericPart.substring(0, 1));
            if (depCode < 0 || depCode > 8) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }
    
    public String getDepartamento(String ci) {
        if (!isValid(ci)) return "Desconocido";
        
        String numericPart = cleanNumber(ci).replaceAll("[^0-9]", "");
        int depCode = Integer.parseInt(numericPart.substring(0, 1));
        
        return DEPARTAMENTOS[depCode];
    }

    @Override
    public String format(String documentNumber) {
        // Eliminar caracteres no numéricos
        String cleaned = documentNumber.replaceAll("[^0-9]", "");
        
        // Validar CI_BO (8 dígitos)
        if (cleaned.matches("\\d{8}")) {
            return cleaned;
        }
        
        return documentNumber; // Devolver original si no cumple formato
    }

    @Override
    public String getDocumentType() {
        return "Cédula de Identidad Boliviana (CI)";
    }
}