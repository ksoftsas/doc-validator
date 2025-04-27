package com.ksoft.validation.core.algorithm.country.pe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PeruDniValidatorTest {
    private final PeruDniValidator validator = new PeruDniValidator();

    @Test
    void testValidDni() {
        assertTrue(validator.isValid("12345678-K")); // DNI válido con K
        assertTrue(validator.isValid("123456780"));  // DNI válido con 0
        assertTrue(validator.isValid("12345678-0")); // Con formato
    }

    @Test
    void testInvalidDni() {
        assertFalse(validator.isValid("12345678-1")); // DV incorrecto
        assertFalse(validator.isValid("1234567"));    // Muy corto
    }

    @Test
    void testFormatDni() {
        assertEquals("12345678-0", validator.format("123456780"));
    }
}