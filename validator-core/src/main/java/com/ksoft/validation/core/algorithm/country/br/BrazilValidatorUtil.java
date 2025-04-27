package com.ksoft.validation.core.algorithm.country.br;

public class BrazilValidatorUtil {
    
    // Método para extraer la región del CPF (primer dígito)
    public static String getCpfRegion(String cpf) {
        if (cpf == null || cpf.length() < 1) return "Desconhecida";
        
        char firstDigit = cpf.charAt(0);
        switch (firstDigit) {
            case '0': return "RS";
            case '1': return "DF, GO, MS, MT e TO";
            case '2': return "AC, AM, AP, PA, RO e RR";
            case '3': return "CE, MA e PI";
            case '4': return "AL, PB, PE, RN";
            case '5': return "BA e SE";
            case '6': return "MG";
            case '7': return "ES e RJ";
            case '8': return "SP";
            case '9': return "PR e SC";
            default: return "Desconhecida";
        }
    }
    
    // Método para validar estado por UF
    public static boolean isValidUF(String uf) {
        String[] ufs = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", 
                        "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", 
                        "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
        
        for (String validUF : ufs) {
            if (validUF.equalsIgnoreCase(uf)) {
                return true;
            }
        }
        return false;
    }
}