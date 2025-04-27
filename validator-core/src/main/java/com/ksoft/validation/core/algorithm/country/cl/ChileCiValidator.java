package com.ksoft.validation.core.algorithm.country.cl;

import com.ksoft.validation.core.algorithm.DocumentValidator;

public class ChileCiValidator implements DocumentValidator {
    
    // Usamos el validador de RUT internamente ya que comparten algoritmo
    private final ChileRutValidator rutValidator = new ChileRutValidator();
    
    // Patrón específico para CI (similar a RUT pero con posibles prefijos diferentes)
    private static final String CI_PATTERN = "^[0-9]{7,8}-[0-9kK]$";
    
    @Override
    public boolean isValid(String documentNumber) {
        // Primero validar el formato básico
        String cleaned = cleanFormat(documentNumber);
        if (!cleaned.matches(CI_PATTERN)) {
            return false;
        }
        
        // Luego validar usando el algoritmo de RUT
        return rutValidator.isValid(documentNumber);
    }
    
    // Método para limpiar y estandarizar el formato
    private String cleanFormat(String ci) {
        // Eliminar puntos, espacios y normalizar K mayúscula
        return ci.replaceAll("[^0-9kK-]", "").toUpperCase();
    }
    
    // Método para determinar si es CI de extranjero
    public boolean isExtranjero(String ci) {
        if (!isValid(ci)) return false;
        
        String cleaned = cleanFormat(ci);
        String numberPart = cleaned.split("-")[0];
        
        // Los números que comienzan con 50-59 son para extranjeros
        if (numberPart.length() >= 2) {
            int firstTwoDigits = Integer.parseInt(numberPart.substring(0, 2));
            return firstTwoDigits >= 50 && firstTwoDigits <= 59;
        }
        return false;
    }
    
    // Método para formatear la CI consistentemente
    public String formatCi(String ci) {
        if (!isValid(ci)) return ci;
        return rutValidator.formatRut(ci); // Reutilizamos el formateo de RUT
    }
    
    // Método para extraer solo los dígitos (sin DV ni formato)
    public String extractBaseNumber(String ci) {
        if (!isValid(ci)) return ci;
        String cleaned = cleanFormat(ci);
        return cleaned.split("-")[0];
    }
}