package com.ksoft.validation.core.algorithm.country.sv;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class ElSalvadorNitValidator extends Mod11Validator {

    public ElSalvadorNitValidator() {
        // Pesos específicos para el NIT salvadoreño
        super(new int[]{4, 3, 2, 7, 6, 5, 4, 3, 2}, true, 14, false);
    }

    @Override
    public String getDocumentType() {
        return "NIT Salvadoreño";
    }
}