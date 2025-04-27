package com.ksoft.validation.core.algorithm.country.br;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class BrazilCnpjValidator implements DocumentValidator {
    
    private static final String CNPJ_PATTERN = "^[0-9]{14}$";
    private static final int[] DV1_WEIGHTS = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] DV2_WEIGHTS = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(CNPJ_PATTERN)) {
            return false;
        }
        
        // Validar dígitos repetidos (CNPJ inválido)
        if (allDigitsSame(cleaned)) {
            return false;
        }
        
        // Calcular y validar dígitos verificadores
        int dv1 = calculateDigit(cleaned.substring(0, 12), DV1_WEIGHTS);
        int dv2 = calculateDigit(cleaned.substring(0, 12) + dv1, DV2_WEIGHTS);
        
        return (cleaned.charAt(12) - '0' == dv1) && 
               (cleaned.charAt(13) - '0' == dv2);
    }
    
    private int calculateDigit(String base, int[] weights) {
        int sum = 0;
        
        for (int i = 0; i < base.length(); i++) {
            sum += (base.charAt(i) - '0') * weights[i];
        }
        
        int remainder = sum % 11;
        return (remainder < 2) ? 0 : (11 - remainder);
    }
    
    private boolean allDigitsSame(String cnpj) {
        char firstDigit = cnpj.charAt(0);
        return cnpj.chars().allMatch(c -> c == firstDigit);
    }
    
    public String formatCnpj(String cnpj) {
        if (!isValid(cnpj)) return cnpj;
        
        return String.format("%s.%s.%s/%s-%s", 
            cnpj.substring(0, 2),
            cnpj.substring(2, 5),
            cnpj.substring(5, 8),
            cnpj.substring(8, 12),
            cnpj.substring(12));
    }

    @Override
    public String format(String documentNumber) {
        // Eliminar caracteres no numéricos ni puntos/guiones
        String cleaned = documentNumber.replaceAll("[^0-9./-]", "");
        
        // Validar CNPJ (XX.XXX.XXX/XXXX-XX)
        if (cleaned.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}")) {
            return cleaned;
        }
        
        return documentNumber; // Devolver original si no cumple formato
    }

    @Override
    public String getDocumentType() {
        return "Cadastro Nacional da Pessoa Jurídica (CNPJ)";
    }
}