package com.ksoft.validation.core.algorithm.country.bo;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class BoliviaNitValidator extends Mod11Validator {
    public BoliviaNitValidator() {
        super(new int[]{1, 2, 3, 4, 5, 6, 7}, // Pesos bolivianos
              false, // Orden normal
              7,     // Longitud fija (6 dígitos + DV)
              false); // No permite 'K' como DV
    }
    
    @Override
    public String getDocumentType() {
        return "Número de Identificación Tributaria (NIT) Boliviano";
    }
    
    @Override
    public String format(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        if (cleaned.length() != 7) return documentNumber;
        return cleaned.substring(0, 6) + "-" + cleaned.charAt(6); // XXXXXX-X
    }
}