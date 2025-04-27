package com.ksoft.validation.core.algorithm.country.cr;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CostaRicaCrValidatorTest {
    private final CostaRicaCrValidator validator = new CostaRicaCrValidator();

    @Test
    void testValidCr() {
        assertTrue(validator.isValid("123456789")); // CR física válida
        assertTrue(validator.isValid("1-2345-6789")); // Con formato
        assertTrue(validator.isValid("123456789012")); // CR jurídica
    }

    @Test
    void testInvalidCr() {
        assertFalse(validator.isValid("12345678")); // CR física inválida (falta DV)
        assertFalse(validator.isValid("A23456789")); // Letras inválidas
    }

    @Test
    void testFormatCr() {
        assertEquals("1-2345-6789", validator.format("123456789"));
    }
}