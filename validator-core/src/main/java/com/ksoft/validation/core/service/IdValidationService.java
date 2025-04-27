package com.ksoft.validation.core.service;

import com.ksoft.validation.core.model.Country;
import com.ksoft.validation.core.model.DocumentType;

public interface IdValidationService {
    boolean validate(Country country, DocumentType type, String documentNumber);
}