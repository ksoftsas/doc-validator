package com.ksoft.validation.core.algorithm.country.uy;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class UruguayCiValidator extends Mod11Validator {

    public UruguayCiValidator() {
        // Pesos específicos para la CI uruguaya
        super(new int[]{2, 9, 8, 7, 6, 3, 4}, true, 8, false);
    }

    @Override
    public String getDocumentType() {
        return "Cédula de Identidad Uruguaya";
    }
}