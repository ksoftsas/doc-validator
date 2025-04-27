package com.ksoft.validation.core.algorithm.country.mx;

import com.ksoft.validation.core.algorithm.DocumentValidator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MexicoCurpValidator implements DocumentValidator {
    
    private static final String CURP_PATTERN = "^[A-Z]{4}[0-9]{6}[HM][A-Z]{5}[0-9A-Z][0-9]$";
    private static final Set<String> ENTIDADES_FEDERATIVAS = new HashSet<>(Arrays.asList(
        "AS", "BC", "BS", "CC", "CS", "CH", "CL", "CM", "DF", "DG", 
        "GT", "GR", "HG", "JC", "MC", "MN", "MS", "NT", "NL", "OC", 
        "PL", "QT", "QR", "SP", "SL", "SR", "TC", "TS", "TL", "VZ", 
        "YN", "ZS", "NE"
    ));
    private static final Set<String> PALABRAS_INCONVENIENTES = new HashSet<>(Arrays.asList(
        "BACA", "BAKA", "BUEI", "BUEY", "CACA", "CACO", "CAGA", "CAGO", 
        "CAKA", "CAKO", "COGE", "COGI", "COJA", "COJE", "COJI", "COJO", 
        "COLA", "CULO", "FALO", "FETO", "GETA", "GUEI", "GUEY", "JETA", 
        "JOTO", "KACA", "KACO", "KAGA", "KAGO", "KAKA", "KAKO", "KOGE", 
        "KOGI", "KOJA", "KOJE", "KOJI", "KOJO", "KOLA", "KULO", "LILO", 
        "LOCA", "LOCO", "LOKA", "LOKO", "MAME", "MAMO", "MEAR", "MEAS", 
        "MEON", "MIAR", "MION", "MOCO", "MOKO", "MULA", "MULO", "NACA", 
        "NACO", "PEDA", "PEDO", "PENE", "PIPI", "PITO", "POPO", "PUTA", 
        "PUTO", "QULO", "RATA", "ROBA", "ROBE", "ROBO", "RUIN", "SENO", 
        "TETA", "VACA", "VAGA", "VAGO", "VAKA", "VUEI", "VUEY", "WUEI", 
        "WUEY"
    ));
    
    @Override
    public boolean isValid(String documentNumber) {
        String curp = cleanNumber(documentNumber).toUpperCase();
        
        // Validar formato básico
        if (!curp.matches(CURP_PATTERN)) {
            return false;
        }
        
        // Validar longitud (18 caracteres)
        if (curp.length() != 18) {
            return false;
        }
        
        // Extraer componentes
        String iniciales = curp.substring(0, 4);
        String fechaNacimiento = curp.substring(4, 10);
        String sexo = curp.substring(10, 11);
        String entidad = curp.substring(11, 13);
        String consonantes = curp.substring(13, 16);
        String homoclave = curp.substring(16, 17);
        String digitoVerificador = curp.substring(17, 18);
        
        // Validaciones específicas
        return !contienePalabraInconveniente(iniciales)
                && isValidFechaNacimiento(fechaNacimiento)
                && isValidSexo(sexo)
                && isValidEntidad(entidad)
                && isValidDigitoVerificador(curp);
    }
    
    private boolean contienePalabraInconveniente(String iniciales) {
        return PALABRAS_INCONVENIENTES.contains(iniciales);
    }
    
    private boolean isValidFechaNacimiento(String fecha) {
        try {
            int dia = Integer.parseInt(fecha.substring(0, 2));
            int mes = Integer.parseInt(fecha.substring(2, 4));
            int anio = Integer.parseInt(fecha.substring(4, 6));
            
            return dia > 0 && dia <= 31 && 
                   mes > 0 && mes <= 12 && 
                   anio >= 0 && anio <= 99;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private boolean isValidSexo(String sexo) {
        return sexo.equals("H") || sexo.equals("M");
    }
    
    private boolean isValidEntidad(String entidad) {
        return ENTIDADES_FEDERATIVAS.contains(entidad);
    }
    
    private boolean isValidDigitoVerificador(String curp) {
        String base = curp.substring(0, 17);
        char providedDv = curp.charAt(17);
        
        char calculatedDv = calculateVerifierDigit(base);
        return providedDv == calculatedDv;
    }
    
    private char calculateVerifierDigit(String base) {
        String dictionary = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        int sum = 0;
        
        for (int i = 0; i < base.length(); i++) {
            char c = base.charAt(i);
            int value = dictionary.indexOf(c);
            if (value == -1) return '0';
            sum += value * (18 - i);
        }
        
        int remainder = sum % 10;
        int dvValue = (10 - remainder) % 10;
        
        return Character.forDigit(dvValue, 10);
    }
    
    public String formatCurp(String curp) {
        if (!isValid(curp)) return curp;
        return curp.toUpperCase();
    }
}