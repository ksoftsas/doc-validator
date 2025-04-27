package com.ksoft.validation.core.algorithm.country.ve;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VenezuelaCiValidatorTest {
    private final VenezuelaCiValidator validator = new VenezuelaCiValidator();

    @Test
    void testValidCiVenezolana() {
        assertTrue(validator.isValid("V-12345678")); // CI venezolana válida
        assertTrue(validator.isValid("V12345678"));  // Sin guión
    }

    @Test
    void testValidCiExtranjera() {
        assertTrue(validator.isValid("E-87654329")); // CI extranjera válida (DV=9)
        assertTrue(validator.isValid("E87654329"));  // Sin guión
    }

    @Test
    void testInvalidCi() {
        assertFalse(validator.isValid("V-12345679")); // DV incorrecto
        assertFalse(validator.isValid("X-12345678")); // Tipo inválido
    }

    @Test
    void testFormatCi() {
        assertEquals("V-12345678", validator.formatCi("V12345678"));
    }
}