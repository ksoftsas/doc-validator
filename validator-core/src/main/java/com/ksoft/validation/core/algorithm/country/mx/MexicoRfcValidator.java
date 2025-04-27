package com.ksoft.validation.core.algorithm.country.mx;

import com.ksoft.validation.core.algorithm.DocumentValidator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MexicoRfcValidator implements DocumentValidator {
    
    private static final String RFC_PATTERN = "^[A-ZÑ&]{3,4}[0-9]{6}[A-Z0-9]{3}$";
    private static final Set<String> PALABRAS_INCONVENIENTES = new HashSet<>(Arrays.asList(
        "BUEI", "BUEY", "CACA", "CACO", "CAGA", "CAGO", "CAKA", "CAKO", 
        "COGE", "COJA", "COJE", "COJI", "COJO", "CULO", "FETO", "GUEY", 
        "JOTO", "KACA", "KACO", "KAGA", "KAGO", "KOGE", "KOJO", "KAKA", 
        "KULO", "MAME", "MAMO", "MEAR", "MEON", "MION", "MOCO", "MULA", 
        "PEDA", "PEDO", "PENE", "PUTA", "PUTO", "QULO", "RATA", "RUIN"
    ));
    
    @Override
    public boolean isValid(String documentNumber) {
        String rfc = cleanNumber(documentNumber).toUpperCase();
        
        // Validar formato básico
        if (!rfc.matches(RFC_PATTERN)) {
            return false;
        }
        
        // Validar longitud (12 o 13 caracteres)
        if (rfc.length() != 12 && rfc.length() != 13) {
            return false;
        }
        
        // Extraer componentes
        String nombre = rfc.substring(0, rfc.length() - 9);
        String fecha = rfc.substring(rfc.length() - 9, rfc.length() - 3);
        String homoclave = rfc.substring(rfc.length() - 3, rfc.length() - 1);
        char digitoVerificador = rfc.charAt(rfc.length() - 1);
        
        // Validaciones específicas
        return !contienePalabraInconveniente(nombre)
                && isValidFecha(fecha)
                && isValidDigitoVerificador(rfc);
    }
    
    private boolean contienePalabraInconveniente(String nombre) {
        return nombre.length() == 4 && PALABRAS_INCONVENIENTES.contains(nombre);
    }
    
    private boolean isValidFecha(String fecha) {
        try {
            int anio = Integer.parseInt(fecha.substring(0, 2));
            int mes = Integer.parseInt(fecha.substring(2, 4));
            int dia = Integer.parseInt(fecha.substring(4, 6));
            
            return mes > 0 && mes <= 12 && 
                   dia > 0 && dia <= 31 && 
                   anio >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private boolean isValidDigitoVerificador(String rfc) {
        String base = rfc.substring(0, rfc.length() - 1);
        char providedDv = rfc.charAt(rfc.length() - 1);
        
        char calculatedDv = calculateVerifierDigit(base);
        return providedDv == calculatedDv;
    }
    
    private char calculateVerifierDigit(String base) {
        String dictionary = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ ";
        int sum = 0;
        int multiplier = 13;
        
        for (int i = 0; i < base.length(); i++) {
            char c = base.charAt(i);
            int value = dictionary.indexOf(c);
            if (value == -1) return '0';
            sum += value * (multiplier - i);
        }
        
        int remainder = sum % 11;
        int dvValue = 11 - remainder;
        
        if (dvValue == 11) return '0';
        if (dvValue == 10) return 'A';
        
        return Character.forDigit(dvValue, 10);
    }
    
    public String formatRfc(String rfc) {
        if (!isValid(rfc)) return rfc;
        return rfc.toUpperCase();
    }
    
    public String getTipoRfc(String rfc) {
        if (!isValid(rfc)) return "Desconocido";
        return rfc.length() == 12 ? "Persona moral" : "Persona física";
    }
}