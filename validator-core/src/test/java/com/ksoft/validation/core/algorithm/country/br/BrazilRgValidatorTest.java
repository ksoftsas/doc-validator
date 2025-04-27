package com.ksoft.validation.core.algorithm.country.br;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BrazilRgValidatorTest {
    private final BrazilRgValidator validator = new BrazilRgValidator();

    @Test
    void testValidRg() {
        assertTrue(validator.isValid("12345678"));  // Sin DV
        assertTrue(validator.isValid("1234567X"));  // Con DV X
        assertTrue(validator.isValid("1234567-8")); // Con formato
    }

    @Test
    void testInvalidRg() {
        assertFalse(validator.isValid("A2345678")); // Letra no permitida
        assertFalse(validator.isValid("1234567Y")); // DV inválido
    }

    @Test
    void testFormatRg() {
        assertEquals("1234567-X", validator.formatRg("1234567X"));
    }
}