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
        assertEquals("1234-5678", validator.formatLicense("12345678"));
        assertEquals("ABC-123456", validator.formatLicense("ABC123456"));
    }

    @Test
    void testLicenseType() {
        assertEquals("Licencia Antigua (Numérica)", 
            validator.getLicenseType("12345678"));
        assertEquals("Licencia Nueva (Alfanumérica)", 
            validator.getLicenseType("ABC123456"));
    }

    @Test
    void testCommercialLicense() {
        assertTrue(validator.isCommercialLicense("71234567"));
        assertTrue(validator.isCommercialLicense("CBC123456"));
        assertFalse(validator.isCommercialLicense("11234567"));
        assertFalse(validator.isCommercialLicense("ABC123456"));
    }
}