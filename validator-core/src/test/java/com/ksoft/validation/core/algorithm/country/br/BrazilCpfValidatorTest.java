package com.ksoft.validation.core.algorithm.country.br;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BrazilCpfValidatorTest {
    private final BrazilCpfValidator validator = new BrazilCpfValidator();

    @Test
    void testValidCpf() {
        assertTrue(validator.isValid("52998224725")); // CPF válido
        assertTrue(validator.isValid("529.982.247-25")); // Con formato
    }

    @Test
    void testInvalidCpf() {
        assertFalse(validator.isValid("11111111111")); // Dígitos repetidos
        assertFalse(validator.isValid("52998224726")); // DV incorrecto
        assertFalse(validator.isValid("123456"));      // Muy corto
    }

    @Test
    void testFormatCpf() {
        assertEquals("529.982.247-25", validator.format("52998224725"));
    }
}