package com.ksoft.validation.core.algorithm.country.ni;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NicaraguaCiValidatorTest {
    private final NicaraguaCiValidator validator = new NicaraguaCiValidator();

    @Test
    void testValidCi() {
        assertTrue(validator.isValid("00128056741234")); // CI válida
        assertTrue(validator.isValid("001-2805674-12-34")); // Con formato
    }

    @Test
    void testInvalidCi() {
        assertFalse(validator.isValid("00128056741235")); // DV incorrecto
        assertFalse(validator.isValid("0012805674123"));  // Muy corta
    }

    @Test
    void testFormatCi() {
        assertEquals("001-2805674-12-34", validator.format("00128056741234"));
    }
}