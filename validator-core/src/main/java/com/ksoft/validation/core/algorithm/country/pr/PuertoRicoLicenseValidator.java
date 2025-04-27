package com.ksoft.validation.core.algorithm.country.pr;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class PuertoRicoLicenseValidator implements DocumentValidator {
    
    // Formatos comunes de licencia en Puerto Rico:
    // 1) 8 dígitos: 12345678
    // 2) 9 caracteres alfanuméricos: ABC123456
    private static final String LICENSE_PATTERN = "^([0-9]{8}|[A-Za-z]{3}[0-9]{6})$";
    
    @Override
    public boolean isValid(String licenseNumber) {
        String cleaned = cleanLicenseNumber(licenseNumber);
        
        // Validar formato básico
        return cleaned.matches(LICENSE_PATTERN);
    }
    
    private String cleanLicenseNumber(String license) {
        if (license == null) return "";
        // Eliminar espacios y guiones, convertir a mayúsculas
        return license.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
    }
    
    public String formatLicense(String license) {
        String cleaned = cleanLicenseNumber(license);
        
        if (!isValid(cleaned)) {
            return license;
        }
        
        // Formatear según el tipo de licencia
        if (cleaned.length() == 8) { // Formato numérico
            return String.format("%s-%s", 
                cleaned.substring(0, 4), 
                cleaned.substring(4));
        } else { // Formato alfanumérico
            return String.format("%s-%s", 
                cleaned.substring(0, 3), 
                cleaned.substring(3));
        }
    }
    
    public String getLicenseType(String license) {
        String cleaned = cleanLicenseNumber(license);
        
        if (!isValid(cleaned)) {
            return "Desconocido";
        }
        
        // Determinar tipo por formato
        if (cleaned.length() == 8) {
            return "Licencia Antigua (Numérica)";
        } else {
            return "Licencia Nueva (Alfanumérica)";
        }
    }
    
    public boolean isCommercialLicense(String license) {
        String cleaned = cleanLicenseNumber(license);
        
        if (!isValid(cleaned)) {
            return false;
        }
        
        // Licencias comerciales suelen empezar con ciertos prefijos
        if (cleaned.length() == 8) {
            // En formato antiguo, las comerciales empiezan con 7 u 8
            char firstDigit = cleaned.charAt(0);
            return firstDigit == '7' || firstDigit == '8';
        } else {
            // En formato nuevo, las comerciales empiezan con C, D o E
            char firstChar = cleaned.charAt(0);
            return firstChar == 'C' || firstChar == 'D' || firstChar == 'E';
        }
    }
}