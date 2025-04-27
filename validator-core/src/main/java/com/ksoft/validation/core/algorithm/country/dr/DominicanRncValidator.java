package com.ksoft.validation.core.algorithm.country.dr;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class DominicanRncValidator extends Mod11Validator {

    public DominicanRncValidator() {
        // Pesos específicos para el RNC dominicano
        super(new int[]{7, 9, 8, 6, 5, 4, 3, 2}, true, -1, false);
    }

    @Override
    public boolean isValid(String documentNumber) {
        // Limpiar el número: eliminar caracteres no numéricos
        String cleaned = cleanNumber(documentNumber);

        // Validar longitud (9 o 11 dígitos)
        if (!(cleaned.length() == 9 || cleaned.length() == 11)) {
            return false;
        }

        // Delegar la validación del módulo 11 a la superclase
        return super.isValid(cleaned);
    }

    @Override
    public String getDocumentType() {
        return "RNC Dominicano";
    }
}