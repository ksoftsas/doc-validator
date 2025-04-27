package com.ksoft.validation.core.algorithm.country.mx;

import com.ksoft.validation.core.algorithm.BaseDocumentValidator;

public class MexicoCurpValidator extends BaseDocumentValidator {
    @Override
    protected String getDocumentPattern() {
        return "^[A-Z]{4}[0-9]{6}[HM][A-Z]{5}[0-9A-Z][0-9]$";
    }

    @Override
    protected String getDocumentTypeName() {
        return "Clave Única de Registro de Población (CURP) Mexicana";
    }

    @Override
    public boolean isValid(String curp) {
        String cleaned = cleanNumber(curp);

        if (!super.isValid(cleaned)) {
            return false;
        }

        char dv = calculateVerifierDigit(cleaned.substring(0, 17));
        char providedDv = cleaned.charAt(17);

        return providedDv == dv;
    }

    private char calculateVerifierDigit(String base) {
        String dictionary = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        int sum = 0;

        for (int i = 0; i < base.length(); i++) {
            char c = base.charAt(i);
            int value = dictionary.indexOf(c);
            if (value == -1)
                return '0';
            sum += value * (18 - i);
        }

        int remainder = sum % 10;
        int dvValue = (10 - remainder) % 10;

        return Character.forDigit(dvValue, 10);
    }

}