package com.ksoft.validation.core.algorithm.country.bo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoliviaCiValidatorTest {
    private final BoliviaCiValidator validator = new BoliviaCiValidator();

    @Test
    void testValidCi() {
        assertTrue(validator.isValid("1234567")); // CI sin letras
        assertTrue(validator.isValid("12345LP")); // CI con letras de departamento
        assertTrue(validator.isValid("1CB"));     // CI mínima
        assertTrue(validator.isValid("1234567SC")); // CI completa
    }

    @Test
    void testInvalidCi() {
        assertFalse(validator.isValid(""));          // Vacío
        assertFalse(validator.isValid("12345678"));  // Muy largo (8+ dígitos)
        assertFalse(validator.isValid("A234567"));   // Letra al inicio
        assertFalse(validator.isValid("1234567ABC")); // Demasiadas letras
    }

    @Test
    void testDepartamento() {
        assertEquals("LP", validator.getDepartamento("1234567"));
        assertEquals("CB", validator.getDepartamento("2234567CB"));
        assertEquals("SC", validator.getDepartamento("3234567SC"));
    }
}