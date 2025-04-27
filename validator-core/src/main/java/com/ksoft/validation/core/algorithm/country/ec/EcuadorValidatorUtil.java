package com.ksoft.validation.core.algorithm.country.ec;

public class EcuadorValidatorUtil {
    
    public static String extractCiFromRuc(String ruc) {
        if (ruc == null || ruc.length() < 10) return null;
        return ruc.substring(0, 10);
    }
    
    public static boolean isRucValidoPeroNoRegistrado(String ruc) {
        // Implementación real requeriría conexión con el SRI
        // Esta es una implementación básica para pruebas
        return ruc.startsWith("999999999");
    }
    
    public static String getTipoEstablecimiento(String ruc) {
        if (ruc == null || ruc.length() != 13) return "Desconocido";
        
        String establecimiento = ruc.substring(10, 13);
        if (establecimiento.equals("001")) {
            return "Matriz";
        } else if (establecimiento.equals("002")) {
            return "Sucursal";
        }
        return "Establecimiento adicional";
    }
}