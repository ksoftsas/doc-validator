package com.ksoft.validation.core.algorithm.country.gt;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GuatemalaNitValidatorTest {
    private final GuatemalaNitValidator validator = new GuatemalaNitValidator();

    @Test
    void testValidNit() {
        assertTrue(validator.isValid("12345678-9")); // NIT válido
        assertTrue(validator.isValid("123456789"));  // Sin guión
    }

    @Test
    void testInvalidNit() {
        assertFalse(validator.isValid("12345678-0")); // DV incorrecto
        assertFalse(validator.isValid("1234567-8"));  // Muy corto
    }

    @Test
    void testFormatNit() {
        assertEquals("12345678-9", validator.format("123456789"));
    }
}