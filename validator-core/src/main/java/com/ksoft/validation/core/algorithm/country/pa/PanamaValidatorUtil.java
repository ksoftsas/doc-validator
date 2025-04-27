package com.ksoft.validation.core.algorithm.country.pa;

public class PanamaValidatorUtil {
    
    public static String cleanPanamaDocument(String document) {
        if (document == null) return null;
        // Eliminar guiones y espacios, convertir a mayúsculas
        return document.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
    }
    
    public static boolean isCipTemporal(String cip) {
        if (cip == null || cip.length() < 3) return false;
        // CIPs temporales empiezan con PT
        return cip.startsWith("PT");
    }
    
    public static boolean isRucActivo(String ruc) {
        // Implementación real requeriría conexión con la DGI de Panamá
        // Esta es una implementación básica para pruebas
        return !ruc.startsWith("999");
    }
    
    public static String getDistritoFromCip(String cip) {
        if (cip == null || cip.length() < 5) return "Desconocido";
        
        // Los dígitos 3-5 en CIPs largos pueden indicar distrito
        String codigo = cip.substring(2, 5);
        // Implementación real requeriría mapeo completo
        return "Distrito " + codigo;
    }
}