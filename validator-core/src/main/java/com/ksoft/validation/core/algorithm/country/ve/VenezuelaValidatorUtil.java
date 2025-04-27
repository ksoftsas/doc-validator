package com.ksoft.validation.core.algorithm.country.ve;

public class VenezuelaValidatorUtil {
    
    public static String extractCiBase(String ci) {
        if (ci == null) return null;
        String cleaned = ci.replaceAll("[^VEve0-9]", "").toUpperCase();
        if (cleaned.length() >= 2) {
            return cleaned.substring(0, 1) + cleaned.substring(1, cleaned.length() - 1);
        }
        return null;
    }
    
    public static String extractRifBase(String rif) {
        if (rif == null) return null;
        String cleaned = rif.replaceAll("[^JVEGjveg0-9]", "").toUpperCase();
        if (cleaned.length() >= 10) {
            return cleaned.substring(0, 9);
        }
        return null;
    }
    
    public static boolean isCiMenorEdad(String ci) {
        if (ci == null) return false;
        String cleaned = ci.replaceAll("[^VEve0-9]", "").toUpperCase();
        // CIs de menores tienen ciertos patrones en los primeros dígitos
        return cleaned.startsWith("V0") || cleaned.startsWith("V1");
    }
    
    public static boolean isRifExportador(String rif) {
        if (rif == null) return false;
        String cleaned = rif.replaceAll("[^JVEGjveg0-9]", "").toUpperCase();
        // RIFs de exportadores tienen ciertos patrones
        return cleaned.startsWith("J8") || cleaned.startsWith("V8");
    }
}