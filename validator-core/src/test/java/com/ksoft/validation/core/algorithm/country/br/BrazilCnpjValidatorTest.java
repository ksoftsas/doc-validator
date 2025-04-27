package com.ksoft.validation.core.algorithm.country.br;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BrazilCnpjValidatorTest {
    private final BrazilCnpjValidator validator = new BrazilCnpjValidator();

    @Test
    void testValidCnpj() {
        assertTrue(validator.isValid("33400689000109")); // CNPJ válido
        assertTrue(validator.isValid("33.400.689/0001-09")); // Con formato
    }

    @Test
    void testInvalidCnpj() {
        assertFalse(validator.isValid("11111111111111")); // Dígitos repetidos
        assertFalse(validator.isValid("33400689000108")); // DV incorrecto
        assertFalse(validator.isValid("123456"));         // Muy corto
    }

    @Test
    void testFormatCnpj() {
        assertEquals("33.400.689/0001-09", validator.format("33400689000109"));
    }
}