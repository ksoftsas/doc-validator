package com.ksoft.validation.core.algorithm.country.cr;

public class CostaRicaValidatorUtil {
    
    // Método para determinar provincia por cédula (primer dígito)
    public static String getProvinciaByCr(String cr) {
        if (cr == null || cr.length() < 1) return "Desconocida";
        
        char firstDigit = cr.charAt(0);
        switch (firstDigit) {
            case '1': return "San José";
            case '2': return "Alajuela";
            case '3': return "Cartago";
            case '4': return "Heredia";
            case '5': return "Guanacaste";
            case '6': return "Puntarenas";
            case '7': return "Limón";
            case '8': return "Extranjero naturalizado";
            case '9': return "Extranjero con residencia";
            default: return "Desconocida";
        }
    }
    
    // Método para validar cédula jurídica (10-12 dígitos)
    public static boolean isJuridicaCr(String cr) {
        if (cr == null) return false;
        String cleaned = cr.replaceAll("[^0-9]", "");
        return cleaned.length() >= 10 && cleaned.length() <= 12;
    }
    
    // Método para determinar tipo de DIMEX
    public static String getDimexType(String dimex) {
        if (dimex == null || dimex.length() < 1) return "Desconocido";
        
        String cleaned = dimex.replaceAll("[^0-9]", "");
        if (cleaned.length() == 12) {
            return "DIMEX nuevo formato";
        } else if (cleaned.length() == 11) {
            return "DIMEX antiguo formato";
        }
        return "Desconocido";
    }
}