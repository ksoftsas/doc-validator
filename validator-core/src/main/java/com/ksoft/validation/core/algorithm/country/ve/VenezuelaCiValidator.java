package com.ksoft.validation.core.algorithm.country.ve;

import com.ksoft.validation.core.algorithm.BaseDocumentValidator;

public class VenezuelaCiValidator extends BaseDocumentValidator {
    @Override
    protected String getDocumentPattern() {
        return "^[VvEeJjGg][0-9]{3,8}$"; // Letra inicial + 3 a 8 dígitos
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "Cédula de Identidad Venezolana";
    }
    
    @Override
    public boolean isValid(String ciNumber) {
        String cleaned = cleanNumber(ciNumber);
        
        if (!super.isValid(cleaned)) {
            return false;
        }
        
        // Validar letra inicial (V, E, J, G)
        char firstChar = cleaned.charAt(0);
        return "VEJGvejg".indexOf(firstChar) >= 0;
    }
    
    @Override
    public String format(String ciNumber) {
        String cleaned = cleanNumber(ciNumber);
        if (cleaned.length() < 2 || cleaned.length() > 9) {
            return ciNumber;
        }
        
        return cleaned.substring(0, 1).toUpperCase() + "-" + cleaned.substring(1);
    }
}