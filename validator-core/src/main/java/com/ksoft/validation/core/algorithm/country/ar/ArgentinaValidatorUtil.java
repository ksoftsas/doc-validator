package com.ksoft.validation.core.algorithm.country.ar;

public class ArgentinaValidatorUtil {
    
    // Método para extraer el número de DNI desde un CUIT/CUIL
    public static String extractDniFromCuit(String cuit) {
        if (cuit == null || cuit.length() != 11) {
            return null;
        }
        
        // El DNI son los dígitos 2 al 9 (posiciones 1-8 en 0-based)
        // Pero depende del tipo de CUIT/CUIL
        String prefix = cuit.substring(0, 2);
        
        if (prefix.equals("20") || prefix.equals("23") || prefix.equals("24")) {
            // Personas físicas: DNI son posiciones 2-9
            return cuit.substring(2, 9);
        } else if (prefix.equals("27") || prefix.equals("30") || prefix.equals("33") || prefix.equals("34")) {
            // Otros tipos (empresas, etc.)
            return null; // No contiene DNI real
        }
        
        return null;
    }
    
    // Método para determinar el tipo de documento
    public static String getDocumentType(String cuit) {
        if (cuit == null || cuit.length() != 11) {
            return "Desconocido";
        }
        
        String prefix = cuit.substring(0, 2);
        switch (prefix) {
            case "20": return "CUIT Persona Física";
            case "23": return "CUIL";
            case "24": return "CUIT Persona Física";
            case "27": return "CUIT Empresa";
            case "30": return "CUIT Extranjero";
            case "33": return "CUIT Empresa";
            case "34": return "CUIT Empresa";
            default: return "Desconocido";
        }
    }
}