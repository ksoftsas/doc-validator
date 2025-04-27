package com.ksoft.validation.core.algorithm.country.pa;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class PanamaRucValidator extends Mod11Validator {

    public PanamaRucValidator() {
        // Pesos específicos para el RUC panameño
        super(new int[]{3, 2, 7, 6, 5, 4, 3, 2}, true, 12, false);
    }

    @Override
    public String getDocumentType() {
        return "RUC Panameño";
    }
}