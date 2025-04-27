package com.ksoft.validation.core.algorithm.country.co;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

/**
 * Validador específico para el Número de Identificación Tributaria (NIT) de
 * Colombia.
 * Implementa el algoritmo Módulo 11 según las especificaciones de la DIAN.
 */
public class ColombiaNitValidator extends Mod11Validator {

    private static final int[] DIAN_WEIGHTS = { 3, 7, 13, 17, 19, 23, 29, 37, 41, 43, 47, 53, 59, 67, 71 };

    public ColombiaNitValidator() {
        super(new int[] {}, false, 10, false);
    }

    /**
     * Calcula el dígito de verificación para una base de NIT dada, según el
     * algoritmo DIAN.
     *
     * @param base Los 9 dígitos base del NIT (sin el dígito de verificación).
     * @return El carácter del dígito de verificación calculado.
     */
    @Override
    protected char calculateVerifierDigit(String base) {
        if (base == null || base.length() != 9 || !isNumeric(base)) {
            return Character.MIN_VALUE;
        }

        int sum = 0;
        int weightIndex = 0;

        for (int i = base.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(base.charAt(i));

            if (weightIndex < DIAN_WEIGHTS.length) {
                int weight = DIAN_WEIGHTS[weightIndex];
                sum += digit * weight;
                weightIndex++;
            } else
                return Character.MIN_VALUE;
        }

        int remainder = sum % 11;
        int dvValue;

        // Lógica específica DIAN para determinar el DV a partir del residuo
        if (remainder == 0)
            dvValue = 0;
        else if (remainder == 1)
            dvValue = 1;
        else
            dvValue = 11 - remainder;

        return Character.forDigit(dvValue, 10);
    }

    /**
     * Valida si un número de NIT completo (con dígito de verificación) es válido.
     *
     * @param nit El número de NIT a validar (puede incluir guión u otros
     *            caracteres).
     * @return true si el NIT es válido, false en caso contrario.
     */
    @Override
    public boolean isValid(String nit) {
        String cleaned = cleanNumber(nit); // Limpia caracteres no alfanuméricos

        if (cleaned.length() != 10)
            return false;
        if (!isNumeric(cleaned))
            return false;

        String base = cleaned.substring(0, 9);
        char providedDv = cleaned.charAt(9);

        char calculatedDv = calculateVerifierDigit(base);

        if (calculatedDv == Character.MIN_VALUE)
            return false;

        return providedDv == calculatedDv;
    }

    /**
     * Formatea un número de NIT limpio (10 dígitos) al formato estándar
     * XXX.XXX.XXX-Y.
     * (Nota: El formato estándar DIAN no usa puntos, solo guión antes del DV).
     * Formato aplicado: XXXXXXXXX-Y
     *
     * @param nit El número de NIT (preferiblemente ya limpio).
     * @return El NIT formateado o el original si no tiene 10 dígitos.
     */
    @Override
    public String format(String nit) {
        String cleaned = cleanNumber(nit);
        if (cleaned.length() == 10 && isNumeric(cleaned)) {
            return cleaned.substring(0, 9) + "-" + cleaned.charAt(9);
        }
        return nit;
    }

    @Override
    public String getDocumentType() {
        return "Número de Identificación Tributaria (NIT) Colombiano";
    }
}
