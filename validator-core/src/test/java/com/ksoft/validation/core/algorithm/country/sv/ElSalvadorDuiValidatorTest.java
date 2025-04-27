package com.ksoft.validation.core.algorithm.country.sv;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ElSalvadorDuiValidatorTest {
    private final ElSalvadorDuiValidator validator = new ElSalvadorDuiValidator();

    @Test
    void testValidDui() {
        assertTrue(validator.isValid("12345678-9")); // DUI válido
        assertTrue(validator.isValid("123456789"));  // Sin guión
    }

    @Test
    void testInvalidDui() {
        assertFalse(validator.isValid("12345678-0")); // DV incorrecto
        assertFalse(validator.isValid("1234567-8"));  // Muy corto
    }

    @Test
    void testFormatDui() {
        assertEquals("12345678-9", validator.formatDui("123456789"));
    }
}