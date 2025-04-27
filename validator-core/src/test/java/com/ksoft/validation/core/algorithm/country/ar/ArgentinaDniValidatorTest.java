package com.ksoft.validation.core.algorithm.country.ar;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArgentinaDniValidatorTest {
    private final ArgentinaDniValidator validator = new ArgentinaDniValidator();

    @Test
    void testValidDni() {
        assertTrue(validator.isValid("1234567"));  // 7 dígitos
        assertTrue(validator.isValid("12345678")); // 8 dígitos
        assertTrue(validator.isValid("12.345.678")); // Con formato
    }

    @Test
    void testInvalidDni() {
        assertFalse(validator.isValid("123456"));   // Muy corto
        assertFalse(validator.isValid("123456789")); // Muy largo
        assertFalse(validator.isValid("A2345678")); // Letras
        assertFalse(validator.isValid(""));         // Vacío
        assertFalse(validator.isValid(null));       // Null
    }
}