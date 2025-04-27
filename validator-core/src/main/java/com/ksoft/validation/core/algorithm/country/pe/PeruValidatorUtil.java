package com.ksoft.validation.core.algorithm.country.pe;

public class PeruValidatorUtil {
    
    public static String cleanPeruDocument(String document) {
        if (document == null) return null;
        // Eliminar guiones y espacios, convertir a mayúsculas
        return document.replaceAll("[^0-9Kk]", "").toUpperCase();
    }
    
    public static boolean isRucActivo(String ruc) {
        // Implementación real requeriría conexión con SUNAT
        // Esta es una implementación básica para pruebas
        return !ruc.startsWith("99");
    }
    
    public static String getProvinciaFromRuc(String ruc) {
        if (ruc == null || ruc.length() < 4) return "Desconocido";
        
        String codigo = ruc.substring(2, 4);
        // Implementación real requeriría mapeo completo
        return "Provincia " + codigo;
    }
    
    public static boolean isRucExportador(String ruc) {
        if (ruc == null || ruc.length() < 3) return false;
        // RUCs de exportadores tienen códigos especiales
        return ruc.startsWith("15") || ruc.startsWith("16");
    }
}