package com.ksoft.validation.core.algorithm.country.co;

public class ColombiaValidatorUtil {
    
    // Método para determinar el departamento por CC
    public static String getDepartamentoByCc(String cc) {
        if (cc == null || cc.length() < 2) return "Desconocido";
        
        // Los primeros dígitos indican el departamento
        String deptoCode = cc.substring(0, 2);
        switch (deptoCode) {
            case "11": return "Bogotá";
            case "05": return "Antioquia";
            case "08": return "Atlántico";
            case "13": return "Bolívar";
            case "15": return "Boyacá";
            case "17": return "Caldas";
            // ... completar con otros códigos
            default: return "Desconocido";
        }
    }
    
    // Método para validar dígitos de verificación opcionales en CC
    public static boolean isValidCcWithOptionalDv(String cc) {
        if (cc == null || cc.length() < 6) return false;
        
        // Algoritmo alternativo usado por algunos sistemas
        int sum = 0;
        for (int i = 0; i < cc.length() - 1; i++) {
            sum += Character.getNumericValue(cc.charAt(i)) * (i % 7 + 2);
        }
        
        int calculatedDv = sum % 11;
        if (calculatedDv >= 10) calculatedDv = 0;
        
        return Character.getNumericValue(cc.charAt(cc.length() - 1)) == calculatedDv;
    }
    
    // Método para determinar tipo de NIT
    public static String getNitType(String nit) {
        if (nit == null || nit.length() < 2) return "Desconocido";
        
        // El primer dígito indica el tipo
        char firstDigit = nit.charAt(0);
        switch (firstDigit) {
            case '8': return "Empresa";
            case '9': return "Persona natural";
            default: return "Otro";
        }
    }
}