package com.ksoft.validation.core.algorithm.country.uy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UruguayCiValidatorTest {
    private final UruguayCiValidator validator = new UruguayCiValidator();

    @Test
    void testValidCi() {
        assertTrue(validator.isValid("1234567-8")); // CI válida con guión
        assertTrue(validator.isValid("12345678"));  // CI válida sin formato
        assertTrue(validator.isValid("123456.8"));   // CI con punto
    }

    @Test
    void testInvalidCi() {
        assertFalse(validator.isValid("1234567-9")); // DV incorrecto
        assertFalse(validator.isValid("12345"));     // Muy corta
    }

    @Test
    void testFormatCi() {
        assertEquals("1234567.8", validator.formatCi("12345678"));
    }
}