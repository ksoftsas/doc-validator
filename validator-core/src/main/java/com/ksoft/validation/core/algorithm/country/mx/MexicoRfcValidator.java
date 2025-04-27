package com.ksoft.validation.core.algorithm.country.mx;

import com.ksoft.validation.core.algorithm.BaseDocumentValidator;

public class MexicoRfcValidator extends BaseDocumentValidator {
    @Override
    protected String getDocumentPattern() {
        return "^[A-ZÑ&]{3,4}[0-9]{6}[A-Z0-9]{3}$";
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "Registro Federal de Contribuyentes (RFC) Mexicano";
    }
    
    @Override
    public boolean isValid(String rfc) {
        String cleaned = cleanNumber(rfc);
        
        if (!super.isValid(cleaned)) {
            return false;
        }
        
        if (cleaned.length() == 13) {
            return validateVerifierDigit(cleaned);
        }
        return true;
    }
        
    private boolean validateVerifierDigit(String rfc) {
        String base = rfc.substring(0, 12);
        char providedDv = rfc.charAt(12);
        
        // Algoritmo para cálculo del dígito verificador
        String alphabet = "0123456789ABCDEFGHIJKLMN&OPQRSTUVWXYZ Ñ";
        int sum = 0;
        
        for (int i = 0; i < base.length(); i++) {
            char c = base.charAt(i);
            int value = alphabet.indexOf(c);
            sum += value * (13 - i);
        }
        
        int remainder = sum % 11;
        int calculatedDv = (remainder == 0) ? 0 : 11 - remainder;
        
        char expectedDv;
        if (calculatedDv == 10) {
            expectedDv = 'A';
        } else {
            expectedDv = Character.forDigit(calculatedDv, 10);
        }
        
        return Character.toUpperCase(providedDv) == expectedDv;
    }
}