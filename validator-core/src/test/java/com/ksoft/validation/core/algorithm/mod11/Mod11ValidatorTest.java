package com.ksoft.validation.core.algorithm.mod11;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.ksoft.validation.core.algorithm.country.cl.ChileRutValidator;

class Mod11ValidatorTest {
    private final Mod11Validator basicValidator = new Mod11Validator(
        new int[]{2, 3, 4}, false, -1, true
    );

    @Test
    void testBasicValidation() {
        assertTrue(basicValidator.isValid("1232"));  // 1*2 + 2*3 + 3*4 = 2+6+12=20 → 20%11=9 → DV=2
        assertFalse(basicValidator.isValid("1233"));
        assertTrue(basicValidator.isValid("123K")); // Caso con K (10)
    }

    @Test
    void testInvalidInput() {
        assertFalse(basicValidator.isValid(""));
        assertFalse(basicValidator.isValid(null));
        assertFalse(basicValidator.isValid("12A3"));
        assertFalse(basicValidator.isValid("123"));
    }
}

// Pruebas específicas para Chile
class ChileRutValidatorTest {
    private final ChileRutValidator validator = new ChileRutValidator();

    @Test
    void testValidRut() {
        assertTrue(validator.isValid("7628790-K")); // RUT válido conocido
        assertTrue(validator.isValid("7628790k"));  // Versión sin guión
        assertTrue(validator.isValid("12.345.678-5")); // Con puntos
    }

    @Test
    void testInvalidRut() {
        assertFalse(validator.isValid("7628790-1")); // DV incorrecto
        assertFalse(validator.isValid("762879-K"));  // Muy corto
        assertFalse(validator.isValid("7628790-X")); // Letra inválida
    }
}