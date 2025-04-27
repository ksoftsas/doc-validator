package com.ksoft.validation.core.algorithm.country.sv;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class ElSalvadorNitValidator implements DocumentValidator {
    
    // Formato: 4 dígitos + guión + 6 dígitos + guión + 3 dígitos + guión + 1 DV
    private static final String NIT_PATTERN = "^[0-9]{4}-[0-9]{6}-[0-9]{3}-[0-9]$";
    private static final int[] WEIGHTS = {14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar longitud (14 dígitos)
        if (cleaned.length() != 14) {
            return false;
        }
        
        // Extraer base y dígito verificador
        String base = cleaned.substring(0, 13);
        int providedDv = Character.getNumericValue(cleaned.charAt(13));
        
        // Calcular dígito verificador con módulo 11
        int calculatedDv = calculateModulo11(base);
        
        return providedDv == calculatedDv;
    }
    
    private int calculateModulo11(String base) {
        int sum = 0;
        
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(i));
            sum += digit * WEIGHTS[i];
        }
        
        int remainder = sum % 11;
        int dv = 11 - remainder;
        
        // Casos especiales
        if (dv == 11) return 0;
        if (dv == 10) return 1; // En El Salvador, si da 10 se usa 1
        
        return dv;
    }
    
    public String formatNit(String nit) {
        if (!isValid(nit)) return nit;
        
        String cleaned = cleanNumber(nit);
        return String.format("%s-%s-%s-%s",
            cleaned.substring(0, 4),
            cleaned.substring(4, 10),
            cleaned.substring(10, 13),
            cleaned.substring(13));
    }
    
    public String getTipoContribuyente(String nit) {
        if (!isValid(nit)) return "Desconocido";
        
        String cleaned = cleanNumber(nit);
        char primerDigito = cleaned.charAt(0);
        
        switch (primerDigito) {
            case '0': return "Persona natural";
            case '1': return "Persona jurídica";
            case '2': return "Contribuyente especial";
            default: return "Otro";
        }
    }

    @Override
    public String format(String documentNumber) {
        // Eliminar caracteres no numéricos ni guiones
        String cleaned = documentNumber.replaceAll("[^0-9-]", "");
        
        // Validar NIT_SV (formato: XXXXXXXX-X o XXXXXXXXX)
        if (cleaned.matches("\\d{8}-\\d{1}") || cleaned.matches("\\d{9}")) {
            return cleaned;
        }
        
        return documentNumber; // Devolver original si no cumple formato
    }

    @Override
    public String getDocumentType() {
        return "Número de Identificación Tributaria Salvadoreño (NIT)";
    }
}