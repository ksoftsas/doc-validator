package com.ksoft.validation.core.algorithm.country.ar;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class ArgentinaCuitCuilValidator extends Mod11Validator {

    public ArgentinaCuitCuilValidator() {
        // Configuración específica para Argentina:
        super(new int[] { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 }, // Pesos
                false, // No invertir orden
                11, // Longitud fija (XX-XXXXXXXX-X)
                true); // Permite 'K' como DV
    }

    @Override
    public String getDocumentType() {
        return "CUIL/CUIT Argentino";
    }
}