package com.ksoft.validation.core.algorithm.country.ve;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class VenezuelaRifValidator extends Mod11Validator {
    public VenezuelaRifValidator() {
        super(new int[]{4, 3, 2, 7, 6, 5, 4, 3, 2}, // Pesos para RIF
              false,                               // Orden normal
              10,                                  // Longitud fija
              false);                              // No permite 'K'
    }
    
    @Override
    public String getDocumentType() {
        return "Registro de Información Fiscal (RIF) Venezolano";
    }
    
    @Override
    public boolean isValid(String rifNumber) {
        String cleaned = cleanNumber(rifNumber);
        
        // Validar formato básico (letra + 8-9 dígitos + dígito verificador)
        if (!cleaned.matches("^[VvEeJjGgPp][0-9]{8,9}$")) {
            return false;
        }
        
        // Validar dígito verificador con Mod11
        return super.isValid(cleaned.substring(1)); // Validar solo la parte numérica
    }
    
    @Override
    public String format(String rifNumber) {
        String cleaned = cleanNumber(rifNumber);
        if (cleaned.length() < 9 || cleaned.length() > 10) {
            return rifNumber;
        }
        
        // Formato: X-XXXXXXXX-X
        return cleaned.substring(0, 1).toUpperCase() + "-" +
               cleaned.substring(1, cleaned.length() - 1) + "-" +
               cleaned.substring(cleaned.length() - 1);
    }
}