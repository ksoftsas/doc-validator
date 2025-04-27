package com.ksoft.validation.core.algorithm.country.pr;

public class PuertoRicoValidatorUtil {
    
    public static String extractLicenseBase(String license) {
        if (license == null) return null;
        String cleaned = license.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
        
        if (cleaned.length() == 8 || cleaned.length() == 9) {
            return cleaned;
        }
        return null;
    }
    
    public static String getMunicipioFromLicense(String license) {
        // Implementación real requeriría mapeo de códigos
        // Esta es una implementación básica de ejemplo
        if (license == null || license.length() < 2) return "Desconocido";
        
        String prefix = license.substring(0, 2);
        if (prefix.matches("[0-9]{2}")) {
            // Códigos numéricos para formato antiguo
            switch (prefix) {
                case "01": return "San Juan";
                case "02": return "Bayamón";
                // ... completar con otros municipios
                default: return "Desconocido";
            }
        } else if (prefix.matches("[A-Z]{2}")) {
            // Códigos alfabéticos para formato nuevo
            switch (prefix) {
                case "SJ": return "San Juan";
                case "BA": return "Bayamón";
                // ... completar con otros municipios
                default: return "Desconocido";
            }
        }
        return "Desconocido";
    }
    
    public static boolean isTemporaryLicense(String license) {
        if (license == null) return false;
        // Licencias temporales suelen tener prefijo 'T' en el nuevo formato
        return license.toUpperCase().startsWith("T");
    }
}