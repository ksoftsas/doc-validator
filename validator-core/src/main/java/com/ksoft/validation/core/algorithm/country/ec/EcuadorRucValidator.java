package com.ksoft.validation.core.algorithm.country.ec;

import com.ksoft.validation.core.algorithm.DocumentValidator;
import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class EcuadorRucValidator extends Mod11Validator {
    public EcuadorRucValidator() {
        super(new int[]{3, 2, 7, 6, 5, 4, 3, 2}, // Pesos para RUC natural
              false,                              // Orden normal
              13,                                 // Longitud fija
              false);                             // No permite 'K'
    }

    @Override
    public String getDocumentType() {
        return "Registro Único de Contribuyentes (RUC) Ecuatoriano";
    }

    @Override
    public String format(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        if (cleaned.length() != 13) return documentNumber;
        
        // Formato: XXX.XXX.XXXX.XXX
        return cleaned.substring(0, 3) + "." + 
               cleaned.substring(3, 6) + "." + 
               cleaned.substring(6, 10) + "." + 
               cleaned.substring(10);
    }

    @Override
    public boolean isValid(String documentNumber) {
        if (!super.isValid(documentNumber)) {
            return false;
        }
        
        String cleaned = cleanNumber(documentNumber);
        
        // Validar tipo de RUC según los tres primeros dígitos
        int tipo = Integer.parseInt(cleaned.substring(2, 3)); // 3er dígito
        
        // RUC Natural (persona física)
        if (tipo <= 5) {
            // Validar que los primeros 10 dígitos sean una CI válida
            String ciPart = cleaned.substring(0, 10);
            DocumentValidator ciValidator = new EcuadorCiValidator();
            if (!ciValidator.isValid(ciPart)) {
                return false;
            }
            
            // Los últimos 3 dígitos deben ser 001-999
            int establecimiento = Integer.parseInt(cleaned.substring(10));
            return establecimiento >= 1 && establecimiento <= 999;
        }
        // RUC Jurídico o entidad pública
        else {
            // Validar provincia (primeros dos dígitos)
            int provincia = Integer.parseInt(cleaned.substring(0, 2));
            return provincia >= 1 && provincia <= 24;
        }
    }
}