package com.ksoft.validation.core.algorithm.country.sv;

import com.ksoft.validation.core.algorithm.mod10.Mod10Validator;

public class ElSalvadorDuiValidator extends Mod10Validator {

    public ElSalvadorDuiValidator() {
        // Pesos específicos para el DUI salvadoreño
        super(new int[]{2, 7, 6, 5, 4, 3, 2}, true, 9, null);
    }

    @Override
    public String getDocumentType() {
        return "DUI Salvadoreño";
    }
}