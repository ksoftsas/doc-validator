package com.ksoft.validation.core.algorithm.country.co;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class ColombiaCcValidator extends Mod11Validator {

    public ColombiaCcValidator() {
        // Pesos específicos para la CC colombiana
        super(new int[]{3, 7, 13, 17, 19, 23, 29, 37, 41, 43, 47}, true, 10, false);
    }

    @Override
    public String getDocumentType() {
        return "Cédula de Ciudadanía Colombiana";
    }
}