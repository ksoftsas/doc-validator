package com.ksoft.validation.core.algorithm.country.dr;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DominicanCieValidatorTest {
    private final DominicanCieValidator validator = new DominicanCieValidator();

    @Test
    void testValidCie() {
        assertTrue(validator.isValid("00123456789")); // CIE válida
        assertTrue(validator.isValid("001-2345678-9")); // Con formato
    }

    @Test
    void testInvalidCie() {
        assertFalse(validator.isValid("00123456780")); // DV incorrecto
        assertFalse(validator.isValid("0012345678"));  // Muy corta
    }

    @Test
    void testFormatCie() {
        assertEquals("001-2345678-9", validator.formatCie("00123456789"));
    }
}