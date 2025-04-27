package com.ksoft.validation.core.algorithm.country.ni;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class NicaraguaNitValidator extends Mod11Validator {

    public NicaraguaNitValidator() {
        // Pesos específicos para el NIT nicaragüense
        super(new int[]{4, 3, 2, 7, 6, 5, 4, 3, 2}, true, 12, false);
    }

    @Override
    public String getDocumentType() {
        return "NIT Nicaragüense";
    }
}