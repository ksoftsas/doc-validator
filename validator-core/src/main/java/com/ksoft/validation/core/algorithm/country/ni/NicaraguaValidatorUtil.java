package com.ksoft.validation.core.algorithm.country.ni;

public class NicaraguaValidatorUtil {
    
    public static String extractCiBase(String ci) {
        if (ci == null || ci.length() < 14) return null;
        return ci.substring(0, 12);
    }
    
    public static boolean isNitActivo(String nit) {
        // Implementación real requeriría conexión con la DGI
        // Esta es una implementación básica para pruebas
        return !nit.startsWith("9999");
    }
    
    public static String getMunicipioFromCi(String ci) {
        if (ci == null || ci.length() < 5) return "Desconocido";
        
        String codigoMuni = ci.substring(2, 5);
        // Implementación real requeriría mapeo completo
        return "Municipio " + codigoMuni;
    }
}