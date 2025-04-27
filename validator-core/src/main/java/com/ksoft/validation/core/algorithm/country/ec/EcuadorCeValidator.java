package com.ksoft.validation.core.algorithm.country.ec;

import com.ksoft.validation.core.algorithm.BaseDocumentValidator;

public class EcuadorCeValidator extends BaseDocumentValidator {
    @Override
    protected String getDocumentPattern() {
        return "^[1-9][0-9]{7,8}$"; // 8 o 9 dígitos, no puede empezar con 0
    }

    @Override
    protected String getDocumentTypeName() {
        return "Cédula de Extranjería Ecuatoriana";
    }

    @Override
    public String format(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        if (cleaned.length() < 8 || cleaned.length() > 9) return documentNumber;
        
        // Formato: X-XXX-XXXX o XX-XXX-XXXX
        int splitPos = cleaned.length() == 8 ? 1 : 2;
        return cleaned.substring(0, splitPos) + "-" + 
               cleaned.substring(splitPos, splitPos + 3) + "-" + 
               cleaned.substring(splitPos + 3);
    }

    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        if (!cleaned.matches(getDocumentPattern())) {
            return false;
        }
        
        // Validación adicional: primeros dos dígitos = tipo de documento
        int tipo = Integer.parseInt(cleaned.substring(0, cleaned.length() == 8 ? 1 : 2));
        return tipo >= 1 && tipo <= 9; // Tipos válidos del 1 al 9
    }
}
