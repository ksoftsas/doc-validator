package com.ksoft.validation.core.algorithm.country.pr;

import com.ksoft.validation.core.algorithm.BaseDocumentValidator;

public class PuertoRicoSsnValidator extends BaseDocumentValidator {
    @Override
    protected String getDocumentPattern() {
        return "^[0-9]{3}-[0-9]{2}-[0-9]{4}$"; // Formato estándar XXX-XX-XXXX
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "Número de Seguro Social (Puerto Rico)";
    }
    
    @Override
    public boolean isValid(String ssn) {
        String cleaned = cleanNumber(ssn);
        
        if (!super.isValid(cleaned)) {
            return false;
        }
        
        // Validación adicional: primeros 3 dígitos deben ser 580-584
        int prefix = Integer.parseInt(cleaned.substring(0, 3));
        return prefix >= 580 && prefix <= 584;
    }
    
    @Override
    public String format(String ssn) {
        String cleaned = cleanNumber(ssn); // Elimina guiones existentes
        
        if (cleaned.length() != 9) {
            return ssn;
        }
        
        return cleaned.substring(0, 3) + "-" + 
               cleaned.substring(3, 5) + "-" + 
               cleaned.substring(5);
    }
}