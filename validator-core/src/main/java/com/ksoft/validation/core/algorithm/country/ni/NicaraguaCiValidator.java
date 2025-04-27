package com.ksoft.validation.core.algorithm.country.ni;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class NicaraguaCiValidator extends Mod11Validator {

    public NicaraguaCiValidator() {
        // Pesos específicos para la CI nicaragüense
        super(new int[]{2, 3, 4, 5, 6, 7, 8, 9}, true, 9, false);
    }

    @Override
    public String getDocumentType() {
        return "Cédula de Identidad Nicaragüense";
    }
}