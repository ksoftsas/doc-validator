package com.ksoft.validator.api.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.ksoft.validation.core.algorithm.DocumentValidator;
import com.ksoft.validation.core.algorithm.country.ar.ArgentinaValidatorFactory;
import com.ksoft.validation.core.algorithm.country.bo.BoliviaValidatorFactory;
import com.ksoft.validation.core.algorithm.country.br.BrazilValidatorFactory;
import com.ksoft.validation.core.algorithm.country.cl.ChileValidatorFactory;
import com.ksoft.validation.core.algorithm.country.co.ColombiaValidatorFactory;
import com.ksoft.validation.core.algorithm.country.cr.CostaRicaValidatorFactory;
import com.ksoft.validation.core.algorithm.country.dr.DominicanValidatorFactory;
import com.ksoft.validation.core.algorithm.country.ec.EcuadorValidatorFactory;
import com.ksoft.validation.core.algorithm.country.gt.GuatemalaValidatorFactory;
import com.ksoft.validation.core.algorithm.country.hn.HondurasValidatorFactory;
import com.ksoft.validation.core.algorithm.country.mx.MexicoValidatorFactory;
import com.ksoft.validation.core.algorithm.country.ni.NicaraguaValidatorFactory;
import com.ksoft.validation.core.algorithm.country.pa.PanamaValidatorFactory;
import com.ksoft.validation.core.algorithm.country.pe.PeruValidatorFactory;
import com.ksoft.validation.core.algorithm.country.pr.PuertoRicoValidatorFactory;
import com.ksoft.validation.core.algorithm.country.py.ParaguayValidatorFactory;
import com.ksoft.validation.core.algorithm.country.sv.ElSalvadorValidatorFactory;
import com.ksoft.validation.core.algorithm.country.uy.UruguayValidatorFactory;
import com.ksoft.validation.core.algorithm.country.ve.VenezuelaValidatorFactory;
import com.ksoft.validation.core.model.Country;

public class ValidatorFactory {

    private static final Map<Country, Function<String, DocumentValidator>> VALIDATOR_FACTORIES = new HashMap<>();

    static {
        // Inicialización del mapa con las fábricas de cada país
        VALIDATOR_FACTORIES.put(Country.ARGENTINA, ArgentinaValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.BOLIVIA, BoliviaValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.BRASIL, BrazilValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.CHILE, ChileValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.COLOMBIA, ColombiaValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.COSTA_RICA, CostaRicaValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.ECUADOR, EcuadorValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.EL_SALVADOR, ElSalvadorValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.GUATEMALA, GuatemalaValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.HONDURAS, HondurasValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.MEXICO, MexicoValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.NICARAGUA, NicaraguaValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.PANAMA, PanamaValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.PARAGUAY, ParaguayValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.PERU, PeruValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.PUERTO_RICO, PuertoRicoValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.REPUBLICA_DOMINICANA, DominicanValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.URUGUAY, UruguayValidatorFactory::getValidator);
        VALIDATOR_FACTORIES.put(Country.VENEZUELA, VenezuelaValidatorFactory::getValidator);
    }
    
    public static DocumentValidator getValidator(String countryCode, String documentType) {
        Function<String, DocumentValidator> factory = VALIDATOR_FACTORIES.get(Country.valueOf(countryCode));
        if (factory == null) {
            throw new IllegalArgumentException("País no soportado: " + countryCode);
        }
        return factory.apply(documentType);
    }

    public static DocumentValidator getValidator(Country country, String documentType) {
        Function<String, DocumentValidator> factory = VALIDATOR_FACTORIES.get(country);
        if (factory == null) {
            throw new IllegalArgumentException("País no soportado: " + country.getCode());
        }
        return factory.apply(documentType);
    }
}