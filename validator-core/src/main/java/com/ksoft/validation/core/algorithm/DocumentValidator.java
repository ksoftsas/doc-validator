package com.ksoft.validation.core.algorithm;

@FunctionalInterface
public interface DocumentValidator {
    boolean isValid(String documentNumber);
    
    default String cleanNumber(String number) {
        if (number == null) return "";
        return number.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
    }
    
    default boolean isNumeric(String str) {
        return str != null && !str.isEmpty() && str.chars().allMatch(Character::isDigit);
    }
}