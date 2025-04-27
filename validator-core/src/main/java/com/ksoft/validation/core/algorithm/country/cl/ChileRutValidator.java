package com.ksoft.validation.core.algorithm.country.cl;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class ChileRutValidator extends Mod11Validator {
    public ChileRutValidator() {
        super(new int[]{2, 3, 4, 5, 6, 7, 2, 3}, // Pesos chilenos
              true,  // Orden inverso
              -1,    // Longitud variable (acepta 7-9 dígitos + DV)
              true); // Permite 'K' como DV
    }
    
    @Override
    public String getDocumentType() {
        return "Rol Único Tributario (RUT) Chileno";
    }
    
    @Override
    public String format(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        if (cleaned.length() < 2) return documentNumber;
        
        String base = cleaned.substring(0, cleaned.length() - 1);
        char dv = cleaned.charAt(cleaned.length() - 1);
        
        // Formatear con puntos y guión (12.345.678-9)
        StringBuilder formatted = new StringBuilder();
        for (int i = base.length() - 1, counter = 0; i >= 0; i--, counter++) {
            if (counter > 0 && counter % 3 == 0) {
                formatted.insert(0, ".");
            }
            formatted.insert(0, base.charAt(i));
        }
        return formatted.append("-").append(dv).toString();
    }
}