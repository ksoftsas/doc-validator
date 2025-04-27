package com.ksoft.validation.core.algorithm.country.ni;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NicaraguaNitValidatorTest {
    private final NicaraguaNitValidator validator = new NicaraguaNitValidator();

    @Test
    void testValidNit() {
        assertTrue(validator.isValid("12345678-0")); // NIT válido
        assertTrue(validator.isValid("123456780"));  // Sin guión
    }

    @Test
    void testInvalidNit() {
        assertFalse(validator.isValid("12345678-1")); // DV incorrecto
        assertFalse(validator.isValid("1234567-8"));  // Muy corto
    }

    @Test
    void testFormatNit() {
        assertEquals("12345678-0", validator.format("123456780"));
    }
}