package com.ksoft.validation.core.algorithm.country.co;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class ColombiaTiValidator extends Mod11Validator {

    public ColombiaTiValidator() {
        // Pesos específicos para la TI colombiana
        super(new int[]{3, 7, 13, 17, 19, 23, 29, 37, 41, 43, 47}, true, 10, false);
    }

    @Override
    public String getDocumentType() {
        return "Tarjeta de Identidad Colombiana";
    }
}