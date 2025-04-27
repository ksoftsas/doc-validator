package com.ksoft.validation.core.algorithm.country.cl;

import com.ksoft.validation.core.algorithm.BaseDocumentValidator;

public class ChileCiValidator extends BaseDocumentValidator {
    @Override
    protected String getDocumentPattern() {
        return "^[0-9]{7,8}$"; // 7 u 8 dígitos
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "Cédula de Identidad Chilena";
    }
    
    @Override
    public String format(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        // Formato opcional: XX.XXX.XXX
        if (cleaned.length() >= 7) {
            return cleaned.replaceAll("(\\d{2})(\\d{3})(\\d{3})", "$1.$2.$3");
        }
        return cleaned;
    }
}