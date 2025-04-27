package com.ksoft.validation.core.algorithm.country.cl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChileRutValidatorTest {
    private final ChileRutValidator validator = new ChileRutValidator();

    @Test
    void testValidRut() {
        assertTrue(validator.isValid("7628790-K"));  // RUT válido conocido
        assertTrue(validator.isValid("7628790k"));   // Versión sin guión
        assertTrue(validator.isValid("7.628.790-K")); // Con puntos
        assertTrue(validator.isValid("12345678-5")); // Otro RUT válido
    }

    @Test
    void testInvalidRut() {
        assertFalse(validator.isValid("7628790-1")); // DV incorrecto
        assertFalse(validator.isValid("762879-K"));  // Muy corto
        assertFalse(validator.isValid("7628790-X")); // Letra inválida (no K)
        assertFalse(validator.isValid("A628790-K")); // Letras en número
    }

    @Test
    void testFormatRut() {
        assertEquals("7.628.790-K", validator.formatRut("7628790-K"));
        assertEquals("12.345.678-5", validator.formatRut("12345678-5"));
    }
}