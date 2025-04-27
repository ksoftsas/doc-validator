package com.ksoft.validation.core.algorithm.country.gt;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class GuatemalaDpiValidator extends Mod11Validator {

    public GuatemalaDpiValidator() {
        // Pesos específicos para el DPI guatemalteco
        super(new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}, true, 13, false);
    }

    @Override
    public String getDocumentType() {
        return "DPI Guatemalteco";
    }
}