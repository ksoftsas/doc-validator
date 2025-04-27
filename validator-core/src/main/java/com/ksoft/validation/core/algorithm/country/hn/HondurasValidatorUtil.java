package com.ksoft.validation.core.algorithm.country.hn;

public class HondurasValidatorUtil {
    
    public static String extractRtnBase(String rtn) {
        if (rtn == null || rtn.length() < 14) return null;
        return rtn.substring(0, 12);
    }
    
    public static boolean isRtnActivo(String rtn) {
        // Implementación real requeriría conexión con el SAR
        // Esta es una implementación básica para pruebas
        return !rtn.startsWith("9999");
    }
    
    public static String getRegionContribuyente(String rtn) {
        if (rtn == null || rtn.length() < 2) return "Desconocido";
        
        String codigoRegion = rtn.substring(0, 2);
        switch (codigoRegion) {
            case "01": return "Atlántida";
            case "02": return "Colón";
            case "03": return "Comayagua";
            // ... completar con los 18 departamentos
            case "18": return "Yoro";
            default: return "Desconocido";
        }
    }
}