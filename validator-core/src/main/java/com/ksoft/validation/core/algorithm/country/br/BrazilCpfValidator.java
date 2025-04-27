package com.ksoft.validation.core.algorithm.country.br;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class BrazilCpfValidator extends Mod11Validator {

    public BrazilCpfValidator() {
        super(new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2}, true, 11, false);
    }

    @Override
    public String getDocumentType() {
        return "CPF Brasileño";
    }
}