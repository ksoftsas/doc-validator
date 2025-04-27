package com.ksoft.validation.core.algorithm.country.dr;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DominicanRncValidatorTest {
    private final DominicanRncValidator validator = new DominicanRncValidator();

    @Test
    void testValidRnc() {
        assertTrue(validator.isValid("123456789")); // RNC válido
        assertTrue(validator.isValid("12345678-9")); // Con formato
    }

    @Test
    void testInvalidRnc() {
        assertFalse(validator.isValid("123456788")); // DV incorrecto
        assertFalse(validator.isValid("12345678"));  // Muy corto
    }

    @Test
    void testFormatRnc() {
        assertEquals("12345678-9", validator.format("123456789"));
    }
}