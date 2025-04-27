package com.ksoft.validation.core.algorithm.country.ar;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArgentinaCuitCuilValidatorTest {
    private final ArgentinaCuitCuilValidator validator = new ArgentinaCuitCuilValidator();

    @Test
    void testValidCuitCuil() {
        // Ejemplos reales válidos
        assertTrue(validator.isValid("20123456789")); // CUIT persona física
        assertTrue(validator.isValid("23123456784")); // CUIL
        assertTrue(validator.isValid("20-12345678-9")); // Con guiones
        assertTrue(validator.isValid("30-70707070-1")); // Extranjero
    }

    @Test
    void testInvalidCuitCuil() {
        // Dígito verificador incorrecto
        assertFalse(validator.isValid("20123456780"));
        
        // Prefijo inválido
        assertFalse(validator.isValid("25123456789"));
        
        // Longitud incorrecta
        assertFalse(validator.isValid("2012345678"));
        assertFalse(validator.isValid("201234567890"));
        
        // Caracteres no numéricos
        assertFalse(validator.isValid("20A23456789"));
    }

    @Test
    void testSpecialCaseDV10() {
        // Caso especial cuando el DV calculado es 10
        assertTrue(validator.isValid("23222222224")); // DV=10 → se convierte a 4 para prefijo 23
        assertTrue(validator.isValid("20222222229")); // DV=10 → se convierte a 9 para otros prefijos
    }
}