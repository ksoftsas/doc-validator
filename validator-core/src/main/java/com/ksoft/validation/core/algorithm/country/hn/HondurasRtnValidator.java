package com.ksoft.validation.core.algorithm.country.hn;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class HondurasRtnValidator implements DocumentValidator {
    
    // Formato: 14 dígitos (con o sin guiones)
    private static final String RTN_PATTERN = "^[0-9]{4}-?[0-9]{4}-?[0-9]{4}-?[0-9]{2}$";
    private static final int[] WEIGHTS = {2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5, 6, 7};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar longitud (14 dígitos)
        if (cleaned.length() != 14) {
            return false;
        }
        
        // Validar dígitos verificadores (2 últimos dígitos)
        String base = cleaned.substring(0, 12);
        String providedDv = cleaned.substring(12);
        
        String calculatedDv = calculateVerifierDigits(base);
        
        return providedDv.equals(calculatedDv);
    }
    
    private String calculateVerifierDigits(String base) {
        // Primer dígito verificador
        int sum1 = 0;
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(i));
            sum1 += digit * WEIGHTS[i];
        }
        int dv1 = 11 - (sum1 % 11);
        if (dv1 == 11) dv1 = 0;
        if (dv1 == 10) dv1 = 1; // Caso especial para Honduras
        
        // Segundo dígito verificador (incluye el primer DV en el cálculo)
        int sum2 = 0;
        String baseWithDv1 = base + dv1;
        for (int i = 0; i < baseWithDv1.length(); i++) {
            int digit = Character.getNumericValue(baseWithDv1.charAt(i));
            sum2 += digit * WEIGHTS[i+1]; // Usamos pesos desplazados
        }
        int dv2 = 11 - (sum2 % 11);
        if (dv2 == 11) dv2 = 0;
        if (dv2 == 10) dv2 = 1; // Caso especial para Honduras
        
        return String.valueOf(dv1) + dv2;
    }
    
    public String formatRtn(String rtn) {
        if (!isValid(rtn)) return rtn;
        
        String cleaned = cleanNumber(rtn);
        return String.format("%s-%s-%s-%s",
            cleaned.substring(0, 4),
            cleaned.substring(4, 8),
            cleaned.substring(8, 12),
            cleaned.substring(12));
    }
    
    public String getTipoContribuyente(String rtn) {
        if (!isValid(rtn)) return "Desconocido";
        
        char primerDigito = rtn.charAt(0);
        switch (primerDigito) {
            case '0': return "Persona natural";
            case '1': return "Persona jurídica";
            case '2': return "Empresa extranjera";
            case '3': return "Gobierno";
            case '4': return "Organismo internacional";
            default: return "Otro";
        }
    }
}