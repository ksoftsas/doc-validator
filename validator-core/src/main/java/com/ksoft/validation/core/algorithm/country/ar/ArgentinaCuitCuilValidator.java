package com.ksoft.validation.core.algorithm.country.ar;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class ArgentinaCuitCuilValidator extends Mod11Validator {

    private static final int[] ARG_WEIGHTS = { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 };

    public ArgentinaCuitCuilValidator() {
        super(ARG_WEIGHTS, false, 11, false);
    }

    /**
     * Calcula el dígito verificador para CUIT/CUIL argentino.
     * 
     * @param base Los primeros 10 dígitos del CUIT/CUIL.
     * @return El dígito verificador calculado como char.
     */
    @Override
    protected char calculateVerifierDigit(String base) {
        if (base == null || base.length() != 10 || !isNumeric(base))
            return Character.MIN_VALUE;

        int sum = 0;

        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(i));
            sum += digit * ARG_WEIGHTS[i];
        }

        int remainder = sum % 11;
        int dvValue;

        // Lógica específica para CUIT/CUIL
        if (remainder == 0)
            dvValue = 0;
        else if (remainder == 1)
            dvValue = 11 - remainder;
        else
            dvValue = 11 - remainder;

        if (dvValue == 10) {
            return 'K';
        } else if (dvValue == 11) {
            return '0';
        } else {
            return Character.forDigit(dvValue, 10);
        }
    }

    /**
     * Valida un CUIT/CUIL completo (11 dígitos).
     * Reutiliza la lógica de Mod11Validator, que ahora usará
     * el calculateVerifierDigit sobreescrito y respetará allowK=false.
     * 
     * @param documentNumber El CUIT/CUIL a validar.
     * @return true si es válido, false en caso contrario.
     */
    @Override
    public boolean isValid(String documentNumber) {

        String cleaned = cleanNumber(documentNumber);
        if (cleaned.length() != 11 || !isNumeric(cleaned)) {
            return false;
        }

        return super.isValid(documentNumber);
    }

    /**
     * Formatea un CUIT/CUIL limpio (11 dígitos) a XX-XXXXXXXX-X.
     * 
     * @param cuitCuil El número a formatear.
     * @return El número formateado o el original si no es válido.
     */
    @Override
    public String format(String cuitCuil) {
        String cleaned = cleanNumber(cuitCuil);
        if (cleaned.length() == 11 && isNumeric(cleaned)) {
            return String.format("%s-%s-%s",
                    cleaned.substring(0, 2),
                    cleaned.substring(2, 10),
                    cleaned.charAt(10));
        }
        return cuitCuil; // Retorna original si no es formateable
    }

    @Override
    public String getDocumentType() {
        return "CUIL/CUIT Argentino";
    }
}
