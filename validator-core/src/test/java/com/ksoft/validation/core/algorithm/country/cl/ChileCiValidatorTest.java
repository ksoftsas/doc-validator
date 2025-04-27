package com.ksoft.validation.core.algorithm.country.cl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChileCiValidatorTest {
    private final ChileCiValidator validator = new ChileCiValidator();

    @Test
    void testValidCi() {
        assertTrue(validator.isValid("12345678-5")); // CI válida (mismo formato que RUT)
        assertTrue(validator.isValid("50123456-7")); // CI de extranjero
    }

    @Test
    void testIsExtranjero() {
        assertTrue(validator.isExtranjero("50123456-7"));
        assertTrue(validator.isExtranjero("55123456-3"));
        assertFalse(validator.isExtranjero("12345678-5"));
    }
}