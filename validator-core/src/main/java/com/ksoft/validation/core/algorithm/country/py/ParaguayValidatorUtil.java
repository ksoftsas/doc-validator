package com.ksoft.validation.core.algorithm.country.py;

public class ParaguayValidatorUtil {
    
    public static String cleanParaguayDocument(String document) {
        if (document == null) return null;
        // Eliminar guiones, puntos y espacios
        return document.replaceAll("[^0-9]", "");
    }
    
    public static boolean isCiExtranjera(String ci) {
        if (ci == null || ci.length() < 2) return false;
        // CIs de extranjeros empiezan con 9
        return ci.startsWith("9");
    }
    
    public static String getCiTitular(String rucFisica) {
        if (rucFisica == null || rucFisica.length() != 8) return null;
        // Para RUCs físicos, los primeros 6 dígitos son la CI
        return rucFisica.substring(0, 6);
    }
    
    public static String getDvFromRuc(String ruc) {
        if (ruc == null || ruc.length() < 2) return null;
        return ruc.substring(ruc.length() - 1);
    }
}