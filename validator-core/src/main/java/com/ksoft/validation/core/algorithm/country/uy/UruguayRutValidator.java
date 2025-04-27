package com.ksoft.validation.core.algorithm.country.uy;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class UruguayRutValidator extends Mod11Validator {

    public UruguayRutValidator() {
        // Pesos específicos para el RUT uruguayo
        super(new int[]{4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}, true, 12, false);
    }

    @Override
    public String getDocumentType() {
        return "RUT Uruguayo";
    }
}