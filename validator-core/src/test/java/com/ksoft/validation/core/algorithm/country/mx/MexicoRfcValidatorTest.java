package com.ksoft.validation.core.algorithm.country.mx;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MexicoRfcValidatorTest {
    private final MexicoRfcValidator validator = new MexicoRfcValidator();

    @Test
    void testValidRfc() {
        assertTrue(validator.isValid("GODE561231GR8")); // RFC válido
        assertTrue(validator.isValid("GODE561231GR8".toLowerCase())); // Minúsculas
    }

    @Test
    void testInvalidRfc() {
        assertFalse(validator.isValid("GODE561231GR9")); // DV incorrecto
        assertFalse(validator.isValid("BUEI561231GR8")); // Palabra inconveniente
        assertFalse(validator.isValid("GODE561231GR"));  // Muy corto
    }

    @Test
    void testFormatRfc() {
        assertEquals("GODE561231GR8", validator.formatRfc("gode561231gr8"));
    }
}