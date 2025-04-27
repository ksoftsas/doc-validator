package com.ksoft.validation.core.algorithm;

public abstract class BaseDocumentValidator implements DocumentValidator {
    public String cleanNumber(String documentNumber) {
        return documentNumber.replaceAll("[^0-9]", "");
    }
    
    public boolean isNumeric(String str) {
        return str.matches("[0-9]+");
    }
    
    protected abstract String getDocumentPattern();
    protected abstract String getDocumentTypeName();
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        return cleaned.matches(getDocumentPattern());
    }
    
    @Override
    public String format(String documentNumber) {
        return cleanNumber(documentNumber);
    }
    
    @Override
    public String getDocumentType() {
        return getDocumentTypeName();
    }
}
