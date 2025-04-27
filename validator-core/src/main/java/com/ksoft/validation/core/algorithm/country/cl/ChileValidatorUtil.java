package com.ksoft.validation.core.algorithm.country.cl;

public class ChileValidatorUtil {
    
    // Método para extraer el número sin DV ni formato
    public static String extractBaseNumber(String rut) {
        if (rut == null || !rut.contains("-")) {
            return rut;
        }
        return rut.split("-")[0].replaceAll("[^0-9]", "");
    }
    
    // Método para determinar el tipo de contribuyente
    public static String getTipoContribuyente(String rut) {
        String base = extractBaseNumber(rut);
        if (base == null || base.isEmpty()) {
            return "Desconocido";
        }
        
        // Tomar los primeros 2 dígitos (o menos si el RUT es corto)
        int firstDigits = Integer.parseInt(base.substring(0, Math.min(base.length(), 2)));
        
        if (firstDigits < 10) {
            return "Persona Natural";
        } else if (firstDigits >= 50 && firstDigits <= 59) {
            return "Extranjero";
        } else {
            return "Empresa";
        }
    }
    
    // Método para validar si un RUT es válido pero no existe
    public static boolean isValidButNonexistent(String rut) {
        // Implementación real requeriría conexión con servicio del SII
        // Esta es una implementación básica para pruebas
        String base = extractBaseNumber(rut);
        if (base == null || base.length() < 2) return false;
        
        // Algunos RUTs que pasan validación pero no existen
        return base.startsWith("999") || base.startsWith("000");
    }
}