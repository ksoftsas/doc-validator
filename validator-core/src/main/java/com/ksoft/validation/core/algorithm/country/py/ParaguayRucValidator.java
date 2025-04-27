package com.ksoft.validation.core.algorithm.country.py;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class ParaguayRucValidator extends Mod11Validator {

    public ParaguayRucValidator() {
        // Pesos específicos para el RUC paraguayo
        super(new int[]{3, 2, 7, 6, 5, 4, 3, 2}, true, 11, false);
    }

    @Override
    public String getDocumentType() {
        return "RUC Paraguayo";
    }
}