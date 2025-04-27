package com.ksoft.validation.core.algorithm.country.ar;

import com.ksoft.validation.core.algorithm.BaseDocumentValidator;

public class ArgentinaDniValidator extends BaseDocumentValidator {
    @Override
    protected String getDocumentPattern() {
        return "^[0-9]{7,8}$"; // 7 u 8 dígitos
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "Documento Nacional de Identidad (DNI)";
    }
}