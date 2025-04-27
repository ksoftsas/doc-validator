package com.ksoft.validation.core.algorithm.country.gt;

public class GuatemalaValidatorUtil {
    
    public static String extractDpiParts(String dpi) {
        if (dpi == null || dpi.length() != 13) return null;
        
        return String.format("CUI: %s, Año: %s, Mes: %s, Depto/Muni: %s, Correlativo: %s",
            dpi.substring(0, 4),
            dpi.substring(4, 8),
            dpi.substring(8, 9),
            dpi.substring(9, 11),
            dpi.substring(11, 13));
    }
    
    public static boolean isNitProvisional(String nit) {
        if (nit == null || nit.length() < 9) return false;
        return nit.startsWith("8888");
    }
    
    public static String getMunicipioFromDpi(String dpi) {
        if (dpi == null || dpi.length() < 11) return "Desconocido";
        
        String codigoMuni = dpi.substring(9, 11);
        // Implementación real requeriría mapeo completo
        return "Municipio " + codigoMuni;
    }
}