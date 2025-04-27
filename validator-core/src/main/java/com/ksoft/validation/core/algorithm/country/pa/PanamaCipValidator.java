package com.ksoft.validation.core.algorithm.country.pa;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class PanamaCipValidator implements DocumentValidator {
    
    // Formato: 2 letras + 6-12 dígitos
    private static final String CIP_PATTERN = "^[A-Za-z]{2}[0-9]{6,12}$";
    private static final int[] WEIGHTS_CIP = {2, 3, 4, 5, 6, 7, 8, 9};
    
    @Override
    public boolean isValid(String documentNumber) {
        if (documentNumber == null) return false;
        
        String cleaned = cleanNumber(documentNumber.toUpperCase());
        
        // Validar formato básico
        if (!cleaned.matches(CIP_PATTERN)) {
            return false;
        }
        
        // Extraer partes
        String letterCode = cleaned.substring(0, 2);
        String numberPart = cleaned.substring(2);
        
        // Validar códigos de provincia (primeras dos letras)
        if (!isValidProvinceCode(letterCode)) {
            return false;
        }
        
        // Para CIPs con 8+ dígitos, validar dígito verificador
        if (numberPart.length() >= 8) {
            String base = numberPart.substring(0, numberPart.length() - 1);
            char providedDv = numberPart.charAt(numberPart.length() - 1);
            
            char calculatedDv = calculateVerifierDigit(base);
            return providedDv == calculatedDv;
        }
        
        // CIPs con menos de 8 dígitos no tienen DV
        return true;
    }
    
    private boolean isValidProvinceCode(String code) {
        String[] validCodes = {"PA", "PB", "PC", "PD", "PE", "PF", "PG", "PH", "PI", "PJ", "PK", "PL", "PM"};
        for (String valid : validCodes) {
            if (valid.equals(code)) return true;
        }
        return false;
    }
    
    private char calculateVerifierDigit(String base) {
        int sum = 0;
        
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(i));
            int weight = WEIGHTS_CIP[i % WEIGHTS_CIP.length];
            sum += digit * weight;
        }
        
        int remainder = sum % 11;
        int dvValue = 11 - remainder;
        
        // Casos especiales para Panamá
        if (dvValue == 11) return '0';
        if (dvValue == 10) return '0';
        
        return Character.forDigit(dvValue, 10);
    }
    
    public String formatCip(String cip) {
        if (!isValid(cip)) return cip;
        
        String cleaned = cleanNumber(cip.toUpperCase());
        String letters = cleaned.substring(0, 2);
        String numbers = cleaned.substring(2);
        
        // Formatear según longitud
        if (numbers.length() <= 8) {
            return letters + "-" + numbers;
        } else {
            return letters + "-" + numbers.substring(0, 8) + "-" + numbers.substring(8);
        }
    }
    
    public String getProvince(String cip) {
        if (!isValid(cip)) return "Desconocida";
        
        String code = cip.substring(0, 2).toUpperCase();
        switch (code) {
            case "PA": return "Bocas del Toro";
            case "PB": return "Coclé";
            case "PC": return "Colón";
            case "PD": return "Chiriquí";
            case "PE": return "Darién";
            case "PF": return "Herrera";
            case "PG": return "Los Santos";
            case "PH": return "Panamá";
            case "PI": return "Veraguas";
            case "PJ": return "Kuna Yala";
            case "PK": return "Emberá";
            case "PL": return "Ngäbe-Buglé";
            case "PM": return "Panamá Oeste";
            default: return "Desconocida";
        }
    }

    @Override
    public String format(String documentNumber) {
        // Eliminar caracteres no numéricos
        String cleaned = documentNumber.replaceAll("[^0-9]", "");
        
        // Validar CIP_PA (9 dígitos)
        if (cleaned.matches("\\d{9}")) {
            return cleaned;
        }
        
        return documentNumber; // Devolver original si no cumple formato
    }

    @Override
    public String getDocumentType() {
        return "Cédula de Identidad Personal Panameña (CIP)";
    }
}