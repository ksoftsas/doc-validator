package com.ksoft.validation.core.algorithm.country.pa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PanamaRucValidatorTest {
    private final PanamaRucValidator validator = new PanamaRucValidator();

    @Test
    void testValidNaturalRuc() {
        assertTrue(validator.isValid("10-12345-67-8")); // RUC natural válido
        assertTrue(validator.isValid("1012345678")); // Sin formato
    }

    @Test
    void testValidJuridicaRuc() {
        assertTrue(validator.isValid("123456789-1-23")); // RUC jurídica válido
        assertTrue(validator.isValid("123456789123")); // Sin formato
    }

    @Test
    void testInvalidRuc() {
        assertFalse(validator.isValid("10-12345-67-9")); // DV incorrecto
        assertFalse(validator.isValid("123456789124")); // DV incorrecto
    }

    @Test
    void testFormatRuc() {
        assertEquals("10-12345-67-8", validator.formatRuc("1012345678"));
        assertEquals("123456789-1-23", validator.formatRuc("123456789123"));
    }
}