package com.ksoft.validation.core.algorithm.country.ec;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EcuadorCiValidatorTest {
    private final EcuadorCiValidator validator = new EcuadorCiValidator();

    @Test
    void testValidCi() {
        assertTrue(validator.isValid("1710034065")); // CI válida
        assertTrue(validator.isValid("0926687851")); // Otra CI válida
    }

    @Test
    void testInvalidCi() {
        assertFalse(validator.isValid("1710034066")); // DV incorrecto
        assertFalse(validator.isValid("2510034065")); // Provincia inválida
        assertFalse(validator.isValid("171003406"));  // Muy corta
    }

}