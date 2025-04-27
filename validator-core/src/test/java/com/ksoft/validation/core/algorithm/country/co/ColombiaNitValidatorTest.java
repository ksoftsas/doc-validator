package com.ksoft.validation.core.algorithm.country.co;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ColombiaNitValidatorTest {
    private final ColombiaNitValidator validator = new ColombiaNitValidator(); 

    @Test
    void testValidNit() {
        assertTrue(validator.isValid("800197268-4")); // NIT válido real
        assertTrue(validator.isValid("8001972684"));  // Sin guión
        assertTrue(validator.isValid("890900522-4")); // Persona natural
    }

    @Test
    void testInvalidNit() {
        assertFalse(validator.isValid("800197268-5")); // DV incorrecto
        assertFalse(validator.isValid("80019726-4"));  // Muy corto
    }
}