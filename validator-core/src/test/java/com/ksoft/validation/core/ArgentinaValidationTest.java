package com.ksoft.validation.core;

import com.ksoft.validation.core.model.Country;
import com.ksoft.validation.core.model.DocumentType;
import com.ksoft.validation.core.service.IdValidationService;
import com.ksoft.validation.core.service.impl.IdValidationServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArgentinaValidationTest {
    private final IdValidationService validator = IdValidationServiceImpl.getInstance();

    @Test
    public void testValidCUIT() {
        assertTrue(validator.validate(Country.ARGENTINA, DocumentType.CUIT, "30710000005"));
    }
    
    @Test
    public void testInvalidCUIT() {
        assertFalse(validator.validate(Country.ARGENTINA, DocumentType.CUIT, "20123456780"));
    }
}