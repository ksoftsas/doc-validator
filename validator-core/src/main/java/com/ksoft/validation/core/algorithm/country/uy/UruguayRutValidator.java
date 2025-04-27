package com.ksoft.validation.core.algorithm.country.uy;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class UruguayRutValidator implements DocumentValidator {
    
    // Formato: 12 dígitos (incluyendo dígito verificador)
    private static final String RUT_PATTERN = "^[0-9]{12}$";
    private static final int[] WEIGHTS = {4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(RUT_PATTERN)) {
            return false;
        }
        
        // Validar dígito verificador
        String base = cleaned.substring(0, 11);
        char providedDv = cleaned.charAt(11);
        
        char calculatedDv = calculateVerifierDigit(base);
        return providedDv == calculatedDv;
    }
    
    private char calculateVerifierDigit(String base) {
        int sum = 0;
        
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(i));
            sum += digit * WEIGHTS[i];
        }
        
        int remainder = sum % 11;
        int dvValue = (11 - remainder) % 11;
        
        // Caso especial para Uruguay
        if (dvValue == 10) return '0';
        if (dvValue == 11) return '1';
        
        return Character.forDigit(dvValue, 10);
    }
    
    public String formatRut(String rut) {
        if (!isValid(rut)) return rut;
        
        return String.format("%s.%s.%s-%s",
            rut.substring(0, 2),
            rut.substring(2, 5),
            rut.substring(5, 11),
            rut.substring(11));
    }
    
    public String getTipoContribuyente(String rut) {
        if (!isValid(rut)) return "Desconocido";
        
        String tipo = rut.substring(0, 2);
        switch (tipo) {
            case "10": return "Persona Física";
            case "20": return "Persona Jurídica";
            case "21": return "Empresa Extranjera";
            case "22": return "Organismo Público";
            default: return "Otro";
        }
    }
    
    public boolean isRutActivo(String rut) {
        if (!isValid(rut)) return false;
        // Implementación real requeriría conexión con DGI
        return !rut.startsWith("99");
    }

    @Override
    public String format(String documentNumber) {
        // Eliminar caracteres no numéricos ni guiones
        String cleaned = documentNumber.replaceAll("[^0-9-]", "");
        
        // Validar RUT_UY (formato: X.XXX.XXX-X o XXXXXXXX-X)
        if (cleaned.matches("\\d{1,2}\\.\\d{3}\\.\\d{3}-\\d{1}") || cleaned.matches("\\d{8}-\\d{1}")) {
            return cleaned;
        }
        
        return documentNumber; // Devolver original si no cumple formato
    }

    @Override
    public String getDocumentType() {
        return "Registro Único Tributario Uruguayo (RUT)";
    }
}