package com.ksoft.validation.core.algorithm.country.pr;

import java.util.Arrays;

import com.ksoft.validation.core.algorithm.BaseDocumentValidator;

public class PuertoRicoElectoralIdValidator extends BaseDocumentValidator {
    @Override
    protected String getDocumentPattern() {
        return "^[A-Z]{2}[0-9]{6}$"; // 2 letras + 6 dígitos
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "Identificación Electoral de Puerto Rico";
    }
    
    @Override
    public boolean isValid(String idNumber) {
        String cleaned = cleanNumber(idNumber);
        
        if (!super.isValid(cleaned)) {
            return false;
        }
        
        // Validación adicional: primeras letras deben ser válidas
        String prefix = cleaned.substring(0, 2);
        return isValidPrefix(prefix);
    }
    
    private boolean isValidPrefix(String prefix) {
        // Lista de prefijos válidos para identificaciones electorales
        String[] validPrefixes = {"VA", "VB", "VC", "VD", "VE", "VF", "VG", "VH"};
        return Arrays.asList(validPrefixes).contains(prefix);
    }
    
    @Override
    public String format(String idNumber) {
        String cleaned = cleanNumber(idNumber);
        if (cleaned.length() != 8) {
            return idNumber;
        }
        
        return cleaned.substring(0, 2) + "-" + cleaned.substring(2);
    }
}
