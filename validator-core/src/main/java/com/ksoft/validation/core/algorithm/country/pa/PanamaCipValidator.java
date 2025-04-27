package com.ksoft.validation.core.algorithm.country.pa;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class PanamaCipValidator extends Mod11Validator {

    public PanamaCipValidator() {
        // Pesos específicos para la CIP panameña
        super(new int[]{2, 3, 4, 5, 6, 7, 8, 9}, true, 9, false);
    }

    @Override
    public String getDocumentType() {
        return "Cédula de Identidad Personal Panameña";
    }
}