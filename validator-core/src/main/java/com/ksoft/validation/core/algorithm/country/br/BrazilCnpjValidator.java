package com.ksoft.validation.core.algorithm.country.br;

import com.ksoft.validation.core.algorithm.DocumentValidator; // Asegúrate que esta interfaz exista
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Validador para el Cadastro Nacional da Pessoa Jurídica (CNPJ) de Brasil.
 * Implementa el algoritmo específico de cálculo de dos dígitos verificadores.
 */
public class BrazilCnpjValidator implements DocumentValidator {

    private static final int CNPJ_LENGTH = 14;
    private static final int[] WEIGHTS_DV1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
    private static final int[] WEIGHTS_DV2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

    private static final Set<String> INVALID_CNPJS = new HashSet<>(Arrays.asList(
            "00000000000000", "11111111111111", "22222222222222", "33333333333333",
            "44444444444444", "55555555555555", "66666666666666", "77777777777777",
            "88888888888888", "99999999999999"
    ));


    /**
     * Valida un número de CNPJ brasileño.
     * @param documentNumber El CNPJ a validar (puede contener formato).
     * @return true si el CNPJ es válido, false en caso contrario.
     */
    @Override
    public boolean isValid(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);

        if (cleaned.length() != CNPJ_LENGTH || !isNumeric(cleaned) || INVALID_CNPJS.contains(cleaned)) {
            return false;
        }

        String base1 = cleaned.substring(0, 12);
        char dv1 = calculateDigit(base1, WEIGHTS_DV1);

        if (dv1 != cleaned.charAt(12)) {
            return false;
        }

        String base2 = cleaned.substring(0, 13); // Incluye el primer DV calculado
        char dv2 = calculateDigit(base2, WEIGHTS_DV2);

        return dv2 == cleaned.charAt(13);
    }

    /**
     * Calcula un dígito verificador del CNPJ según los pesos dados.
     * @param base La cadena base sobre la cual calcular.
     * @param weights Los pesos a aplicar.
     * @return El dígito verificador calculado.
     */
    private char calculateDigit(String base, int[] weights) {
        int sum = 0;
        for (int i = 0; i < base.length(); i++) {
            sum += Character.getNumericValue(base.charAt(i)) * weights[i];
        }

        int remainder = sum % 11;
        int dvValue = (remainder < 2) ? 0 : (11 - remainder);

        return Character.forDigit(dvValue, 10);
    }

    /**
     * Formatea un CNPJ limpio (14 dígitos) a XX.XXX.XXX/XXXX-XX.
     * @param documentNumber El CNPJ a formatear.
     * @return El CNPJ formateado o el original si no es válido.
     */
    @Override
    public String format(String documentNumber) {
        String cleaned = cleanNumber(documentNumber);
        if (cleaned.length() == CNPJ_LENGTH && isNumeric(cleaned)) {
            return String.format("%s.%s.%s/%s-%s",
                                 cleaned.substring(0, 2),
                                 cleaned.substring(2, 5),
                                 cleaned.substring(5, 8),
                                 cleaned.substring(8, 12),
                                 cleaned.substring(12, 14));
        }
        return documentNumber;
    }

    /**
     * Obtiene el tipo de documento.
     * @return El nombre del tipo de documento.
     */
    @Override
    public String getDocumentType() {
        return "CNPJ Brasileño";
    }
}
