package com.ksoft.validation.core.algorithm.country.uy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UruguayRutValidatorTest {
    private final UruguayRutValidator validator = new UruguayRutValidator();

    @Test
    void testValidRut() {
        assertTrue(validator.isValid("101234567891")); // RUT válido
        assertTrue(validator.isValid("10.123.456789-1")); // Con formato
    }

    @Test
    void testInvalidRut() {
        assertFalse(validator.isValid("101234567890")); // DV incorrecto
        assertFalse(validator.isValid("10123456789"));  // Muy corto
    }

    @Test
    void testFormatRut() {
        assertEquals("10.123.456789-1", validator.format("101234567891"));
    }
}