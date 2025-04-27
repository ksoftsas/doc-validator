package com.ksoft.validation.core.algorithm.country.co;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class ColombiaNitValidator extends Mod11Validator {

    public ColombiaNitValidator() {
        // Pesos específicos para el NIT colombiano
        super(new int[]{3, 7, 13, 17, 19, 23, 29, 37, 41, 43, 47}, true, 10, false);
    }

    @Override
    protected char calculateVerifierDigit(String base) {
        // Calcular el dígito verificador usando la lógica estándar
        char calculatedDv = super.calculateVerifierDigit(base);

        // Si el dígito verificador es 'K' (valor 10), reemplazarlo por '0'
        if (calculatedDv == 'K') {
            return '0';
        }

        return calculatedDv;
    }

    @Override
    public String getDocumentType() {
        return "NIT Colombiano";
    }
}