package com.ksoft.validation.core.algorithm.country.py;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParaguayRucValidatorTest {
    private final ParaguayRucValidator validator = new ParaguayRucValidator();

    @Test
    void testValidRucFisica() {
        assertTrue(validator.isValid("8000000-1")); // RUC físico válido
        assertTrue(validator.isValid("80000001"));  // Sin formato
    }

    @Test
    void testValidRucJuridica() {
        assertTrue(validator.isValid("80000000-1")); // RUC jurídico válido
        assertTrue(validator.isValid("800000001"));  // Sin formato
    }

    @Test
    void testInvalidRuc() {
        assertFalse(validator.isValid("8000000-2")); // DV incorrecto
        assertFalse(validator.isValid("800000002")); // DV incorrecto
    }

    @Test
    void testFormatRuc() {
        assertEquals("8000000-1", validator.format("80000001"));
        assertEquals("80000000-1", validator.format("800000001"));
    }
}