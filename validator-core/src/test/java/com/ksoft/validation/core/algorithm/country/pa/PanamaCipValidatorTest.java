package com.ksoft.validation.core.algorithm.country.pa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PanamaCipValidatorTest {
    private final PanamaCipValidator validator = new PanamaCipValidator();

    @Test
    void testValidCip() {
        assertTrue(validator.isValid("PE12345678")); // CIP válido con DV
        assertTrue(validator.isValid("PH-123-456-78")); // Con formato
        assertTrue(validator.isValid("PA1234")); // CIP corto sin DV
    }

    @Test
    void testInvalidCip() {
        assertFalse(validator.isValid("XX12345678")); // Código provincia inválido
        assertFalse(validator.isValid("PE12345679")); // DV incorrecto
    }

    @Test
    void testFormatCip() {
        assertEquals("PE-123456-78", validator.formatCip("PE12345678"));
    }
}