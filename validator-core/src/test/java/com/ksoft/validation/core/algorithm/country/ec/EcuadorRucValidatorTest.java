package com.ksoft.validation.core.algorithm.country.ec;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EcuadorRucValidatorTest {
    private final EcuadorRucValidator validator = new EcuadorRucValidator();

    @Test
    void testValidRucNatural() {
        assertTrue(validator.isValid("1710034065001")); // Persona natural
    }

    @Test
    void testValidRucPublico() {
        assertTrue(validator.isValid("1760001550001")); // Empresa pública
    }

    @Test
    void testValidRucPrivado() {
        assertTrue(validator.isValid("1790012344001")); // Sociedad privada
    }

    @Test
    void testInvalidRuc() {
        assertFalse(validator.isValid("1710034065002")); // Establecimiento incorrecto
        assertFalse(validator.isValid("17100340650"));   // Muy corto
    }

    @Test
    void testTipoContribuyente() {
        assertEquals("Persona natural", validator.getTipoContribuyente("1701234567001"));
        assertEquals("Empresa pública", validator.getTipoContribuyente("1761234567001"));
    }
}