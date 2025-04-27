package com.ksoft.validation.core.algorithm.country.gt;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class GuatemalaNitValidator extends Mod11Validator {

    public GuatemalaNitValidator() {
        // Pesos específicos para el NIT guatemalteco
        super(new int[]{2, 3, 4, 5, 6, 7, 8, 9}, true, -1, false);
    }

    @Override
    public boolean isValid(String documentNumber) {
        // Limpiar el número: eliminar caracteres no numéricos
        String cleaned = cleanNumber(documentNumber);

        // Validar longitud (entre 8 y 12 dígitos)
        if (cleaned.length() < 8 || cleaned.length() > 12) {
            return false;
        }

        // Delegar la validación del módulo 11 a la superclase
        return super.isValid(cleaned);
    }

    @Override
    public String getDocumentType() {
        return "NIT Guatemalteco";
    }
}