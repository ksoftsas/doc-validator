package com.ksoft.validation.core.algorithm.country.pe;

import com.ksoft.validation.core.algorithm.BaseDocumentValidator;

public class PeruDniValidator extends BaseDocumentValidator {

    private static final String DNI_PATTERN = "^[0-9]{8}$";

    @Override
    protected String getDocumentPattern() {
        return DNI_PATTERN;
    }

    @Override
    protected String getDocumentTypeName() {
        return "DNI Peruano";
    }
}