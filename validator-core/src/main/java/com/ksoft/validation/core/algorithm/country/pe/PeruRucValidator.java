package com.ksoft.validation.core.algorithm.country.pe;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class PeruRucValidator extends Mod11Validator {

    public PeruRucValidator() {
        // Pesos específicos para el RUC peruano
        super(new int[]{5, 4, 3, 2, 7, 6, 5, 4, 3, 2}, true, 11, false);
    }

    @Override
    public String getDocumentType() {
        return "RUC Peruano";
    }
}