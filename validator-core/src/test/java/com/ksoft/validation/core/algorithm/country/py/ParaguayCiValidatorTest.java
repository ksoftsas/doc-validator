package com.ksoft.validation.core.algorithm.country.py;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParaguayCiValidatorTest {
    private final ParaguayCiValidator validator = new ParaguayCiValidator();

    @Test
    void testValidCi() {
        assertTrue(validator.isValid("1234567-8")); // CI válida con guión
        assertTrue(validator.isValid("12345678"));  // CI válida sin guión
        assertTrue(validator.isValid("123-8"));     // CI corta válida
    }

    @Test
    void testInvalidCi() {
        assertFalse(validator.isValid("1234567-9")); // DV incorrecto
        assertFalse(validator.isValid("123456789")); // Muy larga
    }

    @Test
    void testFormatCi() {
        assertEquals("1234567-8", validator.format("12345678"));
    }
}