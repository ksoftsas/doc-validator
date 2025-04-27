package com.ksoft.validation.core.algorithm.country.br;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class BrazilCnpjValidator extends Mod11Validator {

    public BrazilCnpjValidator() {
        super(new int[]{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}, true, 14, false);
    }

    @Override
    public String getDocumentType() {
        return "CNPJ Brasileño";
    }
}