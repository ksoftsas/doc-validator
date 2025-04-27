package com.ksoft.validation.core.algorithm.country.bo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoliviaNitValidatorTest {
    private final BoliviaNitValidator validator = new BoliviaNitValidator();

    @Test
    void testValidNit() {
        assertTrue(validator.isValid("123456789"));    // Sin DV
        assertTrue(validator.isValid("1234567890"));   // NIT válido con DV 0
        assertTrue(validator.isValid("1028439025"));   // NIT válido con DV 5
        assertTrue(validator.isValid("1663911K"));     // NIT válido con DV K
        assertTrue(validator.isValid("1663911-k"));    // Con guión y minúscula
    }

    @Test
    void testInvalidNit() {
        assertFalse(validator.isValid(""));            // Vacío
        assertFalse(validator.isValid("A23456789"));   // Letra al inicio
        assertFalse(validator.isValid("12345678901X")); // Letra no K
        assertFalse(validator.isValid("12345678901")); // DV incorrecto
    }

    @Test
    void testFormatNit() {
        assertEquals("1234567-8", validator.formatNit("12345678"));
        assertEquals("1663911-K", validator.formatNit("1663911K"));
        assertEquals("1", validator.formatNit("1"));
    }
}