package com.ksoft.validation.core.algorithm.country.dr;

public class DominicanValidatorUtil {
    
    public static String cleanDominicanDocument(String document) {
        if (document == null) return null;
        // Eliminar guiones y espacios
        return document.replaceAll("[^0-9]", "");
    }
    
    public static boolean isCieExtranjera(String cie) {
        if (cie == null || cie.length() < 3) return false;
        // CIE de extranjeros tienen códigos especiales
        return cie.startsWith("999") || cie.startsWith("888");
    }
    
    public static String getMunicipioFromCie(String cie) {
        if (cie == null || cie.length() < 6) return "Desconocido";
        
        String codigo = cie.substring(3, 6);
        // Implementación real requeriría mapeo completo
        return "Municipio " + codigo;
    }
    
    public static boolean isRncContribuyenteEspecial(String rnc) {
        if (rnc == null || rnc.length() < 2) return false;
        // RNCs especiales tienen ciertos prefijos
        return rnc.startsWith("5") || rnc.startsWith("6");
    }
}