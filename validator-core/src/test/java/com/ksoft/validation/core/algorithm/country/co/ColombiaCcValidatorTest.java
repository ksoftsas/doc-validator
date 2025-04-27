package com.ksoft.validation.core.algorithm.country.co;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ColombiaCcValidatorTest {
    private final ColombiaCcValidator validator = new ColombiaCcValidator();

    @Test
    void testValidCc() {
        assertTrue(validator.isValid("12345678"));
        assertTrue(validator.isValid("1.234.567")); // Con formato
        assertTrue(validator.isValid("12345"));      // Mínimo
    }

    @Test
    void testInvalidCc() {
        assertFalse(validator.isValid("1234"));      // Muy corto
        assertFalse(validator.isValid("A2345678"));   // Letras
    }
}