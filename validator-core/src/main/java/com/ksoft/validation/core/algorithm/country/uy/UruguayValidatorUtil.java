package com.ksoft.validation.core.algorithm.country.uy;

public class UruguayValidatorUtil {
    
    public static String cleanUruguayDocument(String document) {
        if (document == null) return null;
        // Eliminar puntos, guiones y espacios
        return document.replaceAll("[^0-9]", "");
    }
    
    public static boolean isCiMenor(String ci) {
        if (ci == null || ci.length() < 2) return false;
        // CIs de menores tienen ciertos prefijos
        return ci.startsWith("1") || ci.startsWith("2");
    }
    
    public static String getCiFromRutFisica(String rut) {
        if (rut == null || rut.length() < 12) return null;
        // Para RUTs de personas físicas, extraer CI
        if (rut.startsWith("10")) {
            return rut.substring(2, 9);
        }
        return null;
    }
    
    public static boolean isRutExportador(String rut) {
        if (rut == null || rut.length() < 3) return false;
        // RUTs de exportadores tienen ciertos códigos
        return rut.startsWith("30") || rut.startsWith("31");
    }
}