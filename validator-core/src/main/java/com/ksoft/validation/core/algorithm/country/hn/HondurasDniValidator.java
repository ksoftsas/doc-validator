package com.ksoft.validation.core.algorithm.country.hn;

import com.ksoft.validation.core.algorithm.BaseDocumentValidator;

public class HondurasDniValidator extends BaseDocumentValidator {
    @Override
    protected String getDocumentPattern() {
        return "^[0-9]{13}$"; // 13 dígitos fijos
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "Documento Nacional de Identidad Hondureño";
    }
    
    @Override
    public String format(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        if (cleaned.length() != 13) return documentNumber;
        
        // Formato: XXXX-XXXX-XXXXX
        return cleaned.substring(0, 4) + "-" + 
               cleaned.substring(4, 8) + "-" + 
               cleaned.substring(8);
    }
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        return cleaned.matches(getDocumentPattern());
    }
}
