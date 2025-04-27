package com.ksoft.validation.core.algorithm.country.mx;

public class MexicoValidatorUtil {
    
    public static String extractFechaNacimientoFromCurp(String curp) {
        if (curp == null || curp.length() < 10) return null;
        return "19" + curp.substring(4, 6) + "-" + curp.substring(6, 8) + "-" + curp.substring(8, 10);
    }
    
    public static String extractNombreFromRfc(String rfc) {
        if (rfc == null || rfc.length() < 4) return null;
        return rfc.substring(0, rfc.length() - 9);
    }
    
    public static String getEntidadFromCurp(String curp) {
        if (curp == null || curp.length() < 13) return "Desconocida";
        String codigo = curp.substring(11, 13);
        
        switch (codigo) {
            case "AS": return "Aguascalientes";
            case "BC": return "Baja California";
            // ... completar con todas las entidades
            case "NE": return "Extranjero";
            default: return "Desconocida";
        }
    }
}