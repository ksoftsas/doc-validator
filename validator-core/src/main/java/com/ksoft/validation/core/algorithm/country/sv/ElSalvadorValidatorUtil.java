package com.ksoft.validation.core.algorithm.country.sv;

public class ElSalvadorValidatorUtil {
    
    public static String extractNitBase(String nit) {
        if (nit == null || nit.length() < 14) return null;
        return nit.substring(0, 13);
    }
    
    public static boolean isNitProvisional(String nit) {
        if (nit == null || nit.length() < 14) return false;
        return nit.startsWith("9999");
    }
    
    public static String getMunicipioFromDui(String dui) {
        if (dui == null || dui.length() < 2) return "Desconocido";
        
        // Los primeros dos dígitos representan departamento y municipio
        // Implementación real requeriría mapeo completo
        return "Municipio " + dui.substring(0, 2);
    }
}