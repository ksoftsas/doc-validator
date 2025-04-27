package com.ksoft.validation.core.algorithm.country.dr;

import com.ksoft.validation.core.algorithm.mod10.Mod10Validator;

public class DominicanCieValidator extends Mod10Validator {

    public DominicanCieValidator() {
        // Pesos específicos para la CIE dominicana
        super(new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1, 2}, true, 11, null);
    }

    @Override
    public String getDocumentType() {
        return "Cédula de Identidad Dominicana";
    }
}