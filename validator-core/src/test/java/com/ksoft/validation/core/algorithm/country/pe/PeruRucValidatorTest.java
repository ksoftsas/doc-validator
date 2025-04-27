package com.ksoft.validation.core.algorithm.country.pe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PeruRucValidatorTest {
    private final PeruRucValidator validator = new PeruRucValidator();

    @Test
    void testValidRucNatural() {
        assertTrue(validator.isValid("10123456789")); // RUC natural válido
        assertTrue(validator.isValid("1012345678-K")); // Con formato y K
    }

    @Test
    void testValidRucJuridica() {
        assertTrue(validator.isValid("20123456789")); // RUC jurídico válido
        assertTrue(validator.isValid("2012345678-0")); // Con formato
    }

    @Test
    void testInvalidRuc() {
        assertFalse(validator.isValid("10123456780")); // DV incorrecto
        assertFalse(validator.isValid("30123456789")); // Tipo inválido
    }

    @Test
    void testFormatRuc() {
        assertEquals("1012345678-9", validator.format("10123456789"));
    }
}