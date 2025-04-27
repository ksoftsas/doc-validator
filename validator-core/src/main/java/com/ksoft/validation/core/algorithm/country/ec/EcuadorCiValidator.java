package com.ksoft.validation.core.algorithm.country.ec;

import com.ksoft.validation.core.algorithm.mod10.Mod10Validator;

public class EcuadorCiValidator extends Mod10Validator {
    public EcuadorCiValidator() {
        super(new int[]{2, 1},    // Pesos alternados 2,1,2,1...
              true,               // Procesar en orden inverso
              10,                 // Longitud fija: 10 dígitos
              "^[1-9][0-9]{9}$"); // Patrón: no puede empezar con 0
    }

    @Override
    public String getDocumentType() {
        return "Cédula de Identidad Ecuatoriana";
    }

    @Override
    public String format(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        if (cleaned.length() != 10) return documentNumber;
        
        // Formato: XX.XXX.XXX
        return cleaned.substring(0, 2) + "." + 
               cleaned.substring(2, 5) + "." + 
               cleaned.substring(5);
    }

    @Override
    public boolean isValid(String documentNumber) {
        if (!super.isValid(documentNumber)) {
            return false;
        }
        
        String cleaned = cleanNumber(documentNumber);
        
        // Validación adicional: primeros dos dígitos = provincia (01-24)
        int provincia = Integer.parseInt(cleaned.substring(0, 2));
        return provincia >= 1 && provincia <= 24;
    }
}