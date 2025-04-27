package com.ksoft.validation.core.algorithm.country.cr;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CostaRicaDimexValidatorTest {
    private final CostaRicaDimexValidator validator = new CostaRicaDimexValidator();

    @Test
    void testValidDimex() {
        assertTrue(validator.isValid("12345678901")); // Formato antiguo
        assertTrue(validator.isValid("112345678901")); // Formato nuevo
    }

    @Test
    void testInvalidDimex() {
        assertFalse(validator.isValid("1234567890")); // Muy corto
        assertFalse(validator.isValid("A2345678901")); // Letras inválidas
    }

    @Test
    void testFormatDimex() {
        assertEquals("112-3456-7890", validator.format("112345678901"));
    }
}