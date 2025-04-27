package com.ksoft.validation.core.algorithm.country.cr;

import com.ksoft.validation.core.algorithm.BaseDocumentValidator;

public class CostaRicaDimexValidator extends BaseDocumentValidator {
    @Override
    protected String getDocumentPattern() {
        return "^[0-9]{11,12}$"; // 11 o 12 dígitos
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "Documento de Identidad Migrante (DIMEX)";
    }
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        if (!cleaned.matches(getDocumentPattern())) {
            return false;
        }
        
        // Validación adicional: Comienza con 100, 200, 300 o 400
        String prefix = cleaned.substring(0, 3);
        return prefix.equals("100") || prefix.equals("200") || 
               prefix.equals("300") || prefix.equals("400");
    }
    
    @Override
    public String format(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        if (cleaned.length() == 11) {
            return cleaned.substring(0, 3) + "-" + 
                   cleaned.substring(3, 7) + "-" + 
                   cleaned.substring(7); // XXX-XXXX-XXXX
        } else if (cleaned.length() == 12) {
            return cleaned.substring(0, 4) + "-" + 
                   cleaned.substring(4, 8) + "-" + 
                   cleaned.substring(8); // XXXX-XXXX-XXXX
        }
        return documentNumber;
    }
}