package com.ksoft.validation.core.algorithm.country.br;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class BrazilCpfValidator implements DocumentValidator {
    
    private static final String CPF_PATTERN = "^[0-9]{11}$";
    private static final int[] DV1_WEIGHTS = {10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] DV2_WEIGHTS = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        
        // Validar formato básico
        if (!cleaned.matches(CPF_PATTERN)) {
            return false;
        }
        
        // Validar dígitos repetidos (CPF inválido)
        if (allDigitsSame(cleaned)) {
            return false;
        }
        
        // Calcular y validar dígitos verificadores
        int dv1 = calculateDigit(cleaned.substring(0, 9), DV1_WEIGHTS);
        int dv2 = calculateDigit(cleaned.substring(0, 9) + dv1, DV2_WEIGHTS);
        
        return (cleaned.charAt(9) - '0' == dv1) && 
               (cleaned.charAt(10) - '0' == dv2);
    }
    
    private int calculateDigit(String base, int[] weights) {
        int sum = 0;
        
        for (int i = 0; i < base.length(); i++) {
            sum += (base.charAt(i) - '0') * weights[i];
        }
        
        int remainder = sum % 11;
        return (remainder < 2) ? 0 : (11 - remainder);
    }
    
    private boolean allDigitsSame(String cpf) {
        char firstDigit = cpf.charAt(0);
        return cpf.chars().allMatch(c -> c == firstDigit);
    }
    
    public String formatCpf(String cpf) {
        if (!isValid(cpf)) return cpf;
        
        return String.format("%s.%s.%s-%s", 
            cpf.substring(0, 3),
            cpf.substring(3, 6),
            cpf.substring(6, 9),
            cpf.substring(9));
    }

    @Override
    public String format(String documentNumber) {
        // Eliminar caracteres no numéricos ni puntos/guiones
        String cleaned = documentNumber.replaceAll("[^0-9.-]", "");
        
        // Validar CPF (XXX.XXX.XXX-XX)
        if (cleaned.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            return cleaned;
        }
        
        return documentNumber; // Devolver original si no cumple formato
    }

    @Override
    public String getDocumentType() {
        return "Cadastro de Pessoas Físicas (CPF)";
    }
}