package com.ksoft.validation.core.algorithm.country.hn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HondurasRtnValidatorTest {
    private final HondurasRtnValidator validator = new HondurasRtnValidator();

    @Test
    void testValidRtn() {
        assertTrue(validator.isValid("0801-1990-0083-25")); // RTN válido con formato
        assertTrue(validator.isValid("08011990008325"));    // RTN válido sin formato
    }

    @Test
    void testInvalidRtn() {
        assertFalse(validator.isValid("0801-1990-0083-26")); // DV incorrecto
        assertFalse(validator.isValid("0801199000832"));     // Muy corto
        assertFalse(validator.isValid("A8011990008325"));    // Letras inválidas
    }

    @Test
    void testFormatRtn() {
        assertEquals("0801-1990-0083-25", validator.formatRtn("08011990008325"));
    }

    @Test
    void testTipoContribuyente() {
        assertEquals("Persona natural", validator.getTipoContribuyente("08011990008325"));
        assertEquals("Persona jurídica", validator.getTipoContribuyente("18011990008325"));
    }
}