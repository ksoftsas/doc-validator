package com.ksoft.validation.core.algorithm.country.ve;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VenezuelaRifValidatorTest {
    private final VenezuelaRifValidator validator = new VenezuelaRifValidator();

    @Test
    void testValidRifJuridico() {
        assertTrue(validator.isValid("J-12345678-9")); // RIF jurídico válido
        assertTrue(validator.isValid("J123456789"));   // Sin guiones
    }

    @Test
    void testValidRifNatural() {
        assertTrue(validator.isValid("V-98765432-1")); // RIF natural válido
        assertTrue(validator.isValid("V987654321"));    // Sin guiones
    }

    @Test
    void testInvalidRif() {
        assertFalse(validator.isValid("J-12345678-0")); // DV incorrecto
        assertFalse(validator.isValid("X-12345678-9")); // Tipo inválido
    }

    @Test
    void testFormatRif() {
        assertEquals("J-12345678-9", validator.formatRif("J123456789"));
    }
}