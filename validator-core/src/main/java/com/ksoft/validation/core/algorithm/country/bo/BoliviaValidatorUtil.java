package com.ksoft.validation.core.algorithm.country.bo;

public class BoliviaValidatorUtil {
    
    // Método para extraer el número base del NIT (sin DV)
    public static String extractNitBase(String nit) {
        if (nit == null || nit.isEmpty()) {
            return null;
        }
        
        String cleaned = nit.replaceAll("[^0-9]", "");
        if (cleaned.length() <= 1) {
            return cleaned;
        }
        
        return cleaned.substring(0, cleaned.length() - 1);
    }
    
    // Método para determinar el tipo de contribuyente por NIT
    public static String getTipoContribuyente(String nit) {
        if (nit == null || nit.isEmpty()) {
            return "Desconocido";
        }
        
        String cleaned = nit.replaceAll("[^0-9]", "");
        if (cleaned.isEmpty()) {
            return "Desconocido";
        }
        
        // El primer dígito indica el tipo de contribuyente
        char firstDigit = cleaned.charAt(0);
        switch (firstDigit) {
            case '1': return "Persona Natural";
            case '2': return "Empresa Privada";
            case '3': return "Empresa Pública";
            case '4': return "Entidad Sin Fines de Lucro";
            case '5': return "Sucursal de Empresa Extranjera";
            default: return "Otro Tipo";
        }
    }
}