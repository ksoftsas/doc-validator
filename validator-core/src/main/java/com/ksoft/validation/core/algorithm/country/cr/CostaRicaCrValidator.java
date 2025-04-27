package com.ksoft.validation.core.algorithm.country.cr;

import com.ksoft.validation.core.algorithm.BaseDocumentValidator;

public class CostaRicaCrValidator extends BaseDocumentValidator {
    @Override
    protected String getDocumentPattern() {
        return "^[0-9]{9}$"; // 9 dígitos fijos
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "Cédula de Identidad Costarricense";
    }
    
    @Override
    public String format(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        if (cleaned.length() != 9) return documentNumber;
        return cleaned.substring(0, 1) + "-" + 
               cleaned.substring(1, 5) + "-" + 
               cleaned.substring(5); // Formato: X-XXXX-XXXX
    }
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        if (!cleaned.matches(getDocumentPattern())) {
            return false;
        }
        
        // Validación adicional: Primer dígito debe ser 1-9
        char firstDigit = cleaned.charAt(0);
        return firstDigit >= '1' && firstDigit <= '9';
    }
}