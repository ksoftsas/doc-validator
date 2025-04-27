package com.ksoft.validation.core.algorithm.country.hn;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class HondurasRtnValidator extends Mod11Validator {
    public HondurasRtnValidator() {
        super(new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10}, // Pesos para RTN
              false,                                 // Orden normal
              14,                                    // Longitud fija
              false);                                // No permite 'K'
    }

    @Override
    public String getDocumentType() {
        return "Registro Tributario Nacional (RTN) Hondureño";
    }

    @Override
    public String format(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        if (cleaned.length() != 14) return documentNumber;
        
        // Formato: XXXX-XXXX-XXXXX
        return cleaned.substring(0, 4) + "-" + 
               cleaned.substring(4, 8) + "-" + 
               cleaned.substring(8);
    }

    @Override
    public boolean isValid(String documentNumber) {
        if (!super.isValid(documentNumber)) {
            return false;
        }
        
        String cleaned = cleanNumber(documentNumber);
        
        // Validación adicional: primeros 4 dígitos deben ser 0001-9999
        int primerosDigitos = Integer.parseInt(cleaned.substring(0, 4));
        return primerosDigitos >= 1 && primerosDigitos <= 9999;
    }
}