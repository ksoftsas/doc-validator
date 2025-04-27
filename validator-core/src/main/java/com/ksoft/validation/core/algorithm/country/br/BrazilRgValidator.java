package com.ksoft.validation.core.algorithm.country.br;

import com.ksoft.validation.core.algorithm.mod11.Mod11Validator;

public class BrazilRgValidator extends Mod11Validator {

    public BrazilRgValidator() {
        // Pesos específicos para el RG brasileño
        super(new int[]{2, 3, 4, 5, 6, 7, 8, 9}, true, 9, false);
    }

    @Override
    public boolean isValid(String documentNumber) {
        // Limpiar el número: eliminar puntos, guiones y espacios
        String cleaned = cleanNumber(documentNumber);

        // Validar longitud exacta
        if (cleaned.length() != 9) {
            return false;
        }

        // Delegar la validación del módulo 11 a la superclase
        return super.isValid(cleaned);
    }

    @Override
    public String format(String documentNumber) {
        // Formatear el número como XX.XXX.XXX-X
        String cleaned = cleanNumber(documentNumber);
        if (cleaned.length() == 9) {
            return String.format("%s.%s.%s-%s",
                    cleaned.substring(0, 2),
                    cleaned.substring(2, 5),
                    cleaned.substring(5, 8),
                    cleaned.substring(8));
        }
        return documentNumber; // Devolver el original si no cumple el formato
    }

    @Override
    public String getDocumentType() {
        return "RG Brasileño";
    }

    @Override
    protected char calculateVerifierDigit(String base) {
        // Sobrescribir para manejar casos especiales del RG (si aplica)
        return super.calculateVerifierDigit(base);
    }
}