package com.ksoft.validation.core.algorithm.country.ec;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class EcuadorRucValidator implements DocumentValidator {
    
    private static final String RUC_PATTERN = "^[0-9]{13}$";
    private final EcuadorCiValidator ciValidator = new EcuadorCiValidator();
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico (13 dígitos)
        if (!cleaned.matches(RUC_PATTERN)) {
            return false;
        }
        
        // Validar según tipo de RUC (tercer dígito)
        int tercerDigito = Character.getNumericValue(cleaned.charAt(2));
        
        if (tercerDigito <= 5) {
            // Persona natural (validar CI + 001)
            return validatePersonaNatural(cleaned);
        } else if (tercerDigito == 6) {
            // Empresa pública (módulo 11)
            return validateEmpresaPublica(cleaned);
        } else if (tercerDigito == 9) {
            // Sociedad privada o extranjero (módulo 11)
            return validateSociedadPrivada(cleaned);
        }
        
        return false;
    }
    
    private boolean validatePersonaNatural(String ruc) {
        // Los primeros 10 dígitos deben ser CI válida
        String ciPart = ruc.substring(0, 10);
        if (!ciValidator.isValid(ciPart)) {
            return false;
        }
        
        // Los últimos 3 dígitos deben ser 001
        String establecimiento = ruc.substring(10);
        return establecimiento.equals("001");
    }
    
    private boolean validateEmpresaPublica(String ruc) {
        // Validar módulo 11 en los primeros 8 dígitos
        String base = ruc.substring(0, 8);
        int providedDv = Character.getNumericValue(ruc.charAt(8));
        
        int calculatedDv = calculateModulo11(base, new int[]{3, 2, 7, 6, 5, 4, 3, 2});
        return calculatedDv == providedDv;
    }
    
    private boolean validateSociedadPrivada(String ruc) {
        // Validar módulo 11 en los primeros 9 dígitos
        String base = ruc.substring(0, 9);
        int providedDv = Character.getNumericValue(ruc.charAt(9));
        
        int calculatedDv = calculateModulo11(base, new int[]{4, 3, 2, 7, 6, 5, 4, 3, 2});
        return calculatedDv == providedDv;
    }
    
    private int calculateModulo11(String base, int[] weights) {
        int sum = 0;
        for (int i = 0; i < base.length(); i++) {
            sum += Character.getNumericValue(base.charAt(i)) * weights[i];
        }
        
        int remainder = sum % 11;
        return (remainder == 0) ? 0 : (11 - remainder);
    }
    
    public String getTipoContribuyente(String ruc) {
        if (!isValid(ruc)) return "Desconocido";
        
        int tercerDigito = Character.getNumericValue(ruc.charAt(2));
        switch (tercerDigito) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: return "Persona natural";
            case 6: return "Empresa pública";
            case 9: return "Sociedad privada o extranjero";
            default: return "Desconocido";
        }
    }

    @Override
    public String format(String documentNumber) {
        // Eliminar caracteres no numéricos
        String cleaned = documentNumber.replaceAll("[^0-9]", "");
        
        // Validar RUC_EC (13 dígitos)
        if (cleaned.matches("\\d{13}")) {
            return cleaned;
        }
        
        return documentNumber; // Devolver original si no cumple formato
    }

    @Override
    public String getDocumentType() {
        return "Registro Único de Contribuyentes Ecuatoriano (RUC)";
    }
}