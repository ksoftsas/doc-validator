package com.ksoft.validation.core.algorithm.country.mx;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class MexicoNssValidator extends Mod11Validator {
    public MexicoNssValidator() {
        super(new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1, 2}, false, 11, false);
    }
    
    @Override
    public String getDocumentType() {
        return "Número de Seguridad Social (NSS) Mexicano";
    }
    
    @Override
    public String format(String nss) {
        String cleaned = cleanNumber(nss);
        if (cleaned.length() != 11) return nss;
        return cleaned.substring(0, 2) + "-" + cleaned.substring(2, 4) + "-" + 
               cleaned.substring(4, 6) + "-" + cleaned.substring(6);
    }
    
    @Override
    public boolean isValid(String nss) {
        if (!super.isValid(nss)) return false;
        
        String cleaned = cleanNumber(nss);
        int subdelegacion = Integer.parseInt(cleaned.substring(0, 2));
        return subdelegacion >= 1 && subdelegacion <= 97;
    }
}