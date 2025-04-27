package com.ksoft.validation.core.algorithm.country.pr;

import com.ksoft.validation.core.algorithm.BaseDocumentValidator;

public class PuertoRicoLicenseValidator extends BaseDocumentValidator {
    @Override
    protected String getDocumentPattern() {
        return "^[A-Z0-9]{8,9}$"; // 8 o 9 caracteres alfanuméricos
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "Licencia de Conducir de Puerto Rico";
    }
    
    @Override
    public boolean isValid(String licenseNumber) {
        String cleaned = cleanNumber(licenseNumber);
        
        if (!super.isValid(cleaned)) {
            return false;
        }
        
        // Validación adicional: debe contener al menos 2 letras
        return cleaned.replaceAll("[^A-Z]", "").length() >= 2;
    }
    
    @Override
    public String format(String licenseNumber) {
        String cleaned = cleanNumber(licenseNumber);
        if (cleaned.length() != 8 && cleaned.length() != 9) {
            return licenseNumber;
        }
        
        // Formato: XXX-XXX-XXX o XX-XXX-XXX
        int splitPos = cleaned.length() == 8 ? 2 : 3;
        return cleaned.substring(0, splitPos) + "-" + 
               cleaned.substring(splitPos, splitPos + 3) + "-" + 
               cleaned.substring(splitPos + 3);
    }
}