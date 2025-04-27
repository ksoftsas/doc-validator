package com.ksoft.validation.core.algorithm.country.sv;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ElSalvadorNitValidatorTest {
    private final ElSalvadorNitValidator validator = new ElSalvadorNitValidator();

    @Test
    void testValidNit() {
        assertTrue(validator.isValid("0614-090789-102-3")); // NIT válido
        assertTrue(validator.isValid("06140907891023"));   // Sin formato
    }

    @Test
    void testInvalidNit() {
        assertFalse(validator.isValid("0614-090789-102-4")); // DV incorrecto
        assertFalse(validator.isValid("0614-090789-102"));   // Incompleto
    }

    @Test
    void testFormatNit() {
        assertEquals("0614-090789-102-3", validator.format("06140907891023"));
    }
}