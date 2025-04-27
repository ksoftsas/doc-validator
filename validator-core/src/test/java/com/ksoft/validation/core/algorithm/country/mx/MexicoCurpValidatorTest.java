package com.ksoft.validation.core.algorithm.country.mx;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MexicoCurpValidatorTest {
    private final MexicoCurpValidator validator = new MexicoCurpValidator();

    @Test
    void testValidCurp() {
        assertTrue(validator.isValid("ROAE800101HDFRNSA1")); // CURP válida
        assertTrue(validator.isValid("ROAE800101HDFRNSA1".toLowerCase())); // Minúsculas
    }

    @Test
    void testInvalidCurp() {
        assertFalse(validator.isValid("ROAE800101HDFRNSA2")); // DV incorrecto
        assertFalse(validator.isValid("BUEI800101HDFRNSA1")); // Palabra inconveniente
        assertFalse(validator.isValid("ROAE800101HDFRNS"));   // Muy corta
    }

    @Test
    void testFormatCurp() {
        assertEquals("ROAE800101HDFRNSA1", validator.formatCurp("roae800101hdfrnsa1"));
    }
}