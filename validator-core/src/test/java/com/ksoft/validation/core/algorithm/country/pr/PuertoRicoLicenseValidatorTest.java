package com.ksoft.validation.core.algorithm.country.pr;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PuertoRicoLicenseValidatorTest {
    private final PuertoRicoLicenseValidator validator = new PuertoRicoLicenseValidator();

    @Test
    void testValidNumericLicense() {
        assertTrue(validator.isValid("12345678")); // Formato numérico
        assertTrue(validator.isValid("12-34-56-78")); // Con formato
    }

    @Test
    void testValidAlphanumericLicense() {
        assertTrue(validator.isValid("ABC123456")); // Formato alfanumérico
        assertTrue(validator.isValid("ABC-123-456")); // Con formato
    }

    @Test
    void testInvalidLicense() {
        assertFalse(validator.isValid("1234567")); // Muy corto
        assertFalse(validator.isValid("ABC12345")); // Formato incorrecto
        assertFalse(validator.isValid("AB1234567")); // Formato incorrecto
    }

    @Test
    void testFormatLicense() {
        assertEquals("1234-5678", validator.format("12345678"));
        assertEquals("ABC-123456", validator.format("ABC123456"));
    }
}