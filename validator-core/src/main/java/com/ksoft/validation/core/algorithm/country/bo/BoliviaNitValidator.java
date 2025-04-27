package com.ksoft.validation.core.algorithm.country.bo;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

/**
 * Validador para el Número de Identificación Tributaria (NIT) de Bolivia.
 * Utiliza el algoritmo Módulo 11 con pesos y reglas específicas de Bolivia.
 * Asume una longitud fija de 7 dígitos (6 base + 1 DV) según la configuración
 * inicial.
 */
public class BoliviaNitValidator extends Mod11Validator {

    private static final int[] BOL_WEIGHTS = { 2, 3, 4, 5, 6, 7 };

    /**
     * Constructor para el validador de NIT boliviano.
     * Configura los pesos, el orden inverso de aplicación y la longitud esperada.
     */
    public BoliviaNitValidator() {
        super(BOL_WEIGHTS, true, 7, false);
    }

    /**
     * Calcula el dígito verificador para una base de NIT boliviano.
     * 
     * @param base Los 6 dígitos base del NIT.
     * @return El dígito verificador calculado como char.
     */
    @Override
    protected char calculateVerifierDigit(String base) {
        if (base == null || base.length() != (expectedLength - 1) || !isNumeric(base))
            return Character.MIN_VALUE;

        int sum = 0;
        int weightIndex = 0;

        for (int i = base.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(base.charAt(i));
            int weight = weights[weightIndex % weights.length];
            sum += digit * weight;
            weightIndex++;
        }

        int remainder = sum % 11;
        int dvValue = 11 - remainder;

        if (dvValue == 10 || dvValue == 11) {
            return '0';
        } else {
            return Character.forDigit(dvValue, 10);
        }
    }

    /**
     * Valida un NIT boliviano completo (7 dígitos).
     * 
     * @param documentNumber El NIT a validar.
     * @return true si es válido, false en caso contrario.
     */
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);

        if (cleaned.length() != expectedLength || !isNumeric(cleaned))
            return false;

        return super.isValid(documentNumber);
    }

    /**
     * Obtiene el tipo de documento.
     * 
     * @return El nombre del tipo de documento.
     */
    @Override
    public String getDocumentType() {
        return "Número de Identificación Tributaria (NIT) Boliviano";
    }

    /**
     * Formatea un NIT boliviano limpio (7 dígitos) a XXXXXX-X.
     * 
     * @param documentNumber El número a formatear.
     * @return El número formateado o el original si no tiene la longitud correcta.
     */
    @Override
    public String format(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        if (cleaned.length() == expectedLength && isNumeric(cleaned))
            return cleaned.substring(0, expectedLength - 1) + "-" + cleaned.charAt(expectedLength - 1);

        return documentNumber;
    }
}
