package com.ksoft.validation.core.algorithm.country.gt;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GuatemalaDpiValidatorTest {
    private final GuatemalaDpiValidator validator = new GuatemalaDpiValidator();

    @Test
    void testValidDpi() {
        assertTrue(validator.isValid("1234567890123")); // DPI válido
        assertTrue(validator.isValid("1234 5678 9012 3")); // Con espacios
    }

    @Test
    void testInvalidDpi() {
        assertFalse(validator.isValid("123456789012")); // Muy corto
        assertFalse(validator.isValid("1234567890124")); // DV incorrecto
    }

    @Test
    void testFormatDpi() {
        assertEquals("1234-5678 901-23", validator.formatDpi("1234567890123"));
    }
}