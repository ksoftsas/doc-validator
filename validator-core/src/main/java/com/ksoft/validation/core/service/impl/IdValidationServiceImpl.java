package com.ksoft.validation.core.service.impl;

import com.ksoft.validation.core.service.IdValidationService;
import com.ksoft.validation.core.model.Country;
import com.ksoft.validation.core.model.DocumentType;
import com.ksoft.validation.core.algorithm.DocumentValidator;
import com.ksoft.validation.core.algorithm.country.ar.ArgentinaCuitCuilValidator;
import com.ksoft.validation.core.algorithm.country.ar.ArgentinaDniValidator;
import com.ksoft.validation.core.algorithm.country.bo.BoliviaCiValidator;
import com.ksoft.validation.core.algorithm.country.bo.BoliviaNitValidator;
import com.ksoft.validation.core.algorithm.country.br.BrazilCnpjValidator;
import com.ksoft.validation.core.algorithm.country.br.BrazilCpfValidator;
import com.ksoft.validation.core.algorithm.country.br.BrazilRgValidator;
import com.ksoft.validation.core.algorithm.country.cl.ChileCiValidator;
import com.ksoft.validation.core.algorithm.country.cl.ChileRutValidator;
import com.ksoft.validation.core.algorithm.country.co.ColombiaCcValidator;
import com.ksoft.validation.core.algorithm.country.co.ColombiaCeValidator;
import com.ksoft.validation.core.algorithm.country.co.ColombiaNitValidator;
import com.ksoft.validation.core.algorithm.country.co.ColombiaTiValidator;
import com.ksoft.validation.core.algorithm.country.cr.CostaRicaCrValidator;
import com.ksoft.validation.core.algorithm.country.cr.CostaRicaDimexValidator;
import com.ksoft.validation.core.algorithm.country.dr.DominicanCieValidator;
import com.ksoft.validation.core.algorithm.country.dr.DominicanRncValidator;
import com.ksoft.validation.core.algorithm.country.ec.EcuadorCiValidator;
import com.ksoft.validation.core.algorithm.country.ec.EcuadorRucValidator;
import com.ksoft.validation.core.algorithm.country.gt.GuatemalaDpiValidator;
import com.ksoft.validation.core.algorithm.country.gt.GuatemalaNitValidator;
import com.ksoft.validation.core.algorithm.country.hn.HondurasRtnValidator;
import com.ksoft.validation.core.algorithm.country.mx.MexicoCurpValidator;
import com.ksoft.validation.core.algorithm.country.mx.MexicoRfcValidator;
import com.ksoft.validation.core.algorithm.country.ni.NicaraguaCiValidator;
import com.ksoft.validation.core.algorithm.country.ni.NicaraguaNitValidator;
import com.ksoft.validation.core.algorithm.country.pa.PanamaCipValidator;
import com.ksoft.validation.core.algorithm.country.pa.PanamaRucValidator;
import com.ksoft.validation.core.algorithm.country.pe.PeruDniValidator;
import com.ksoft.validation.core.algorithm.country.pe.PeruRucValidator;
import com.ksoft.validation.core.algorithm.country.pr.PuertoRicoLicenseValidator;
import com.ksoft.validation.core.algorithm.country.py.ParaguayCiValidator;
import com.ksoft.validation.core.algorithm.country.py.ParaguayRucValidator;
import com.ksoft.validation.core.algorithm.country.sv.ElSalvadorDuiValidator;
import com.ksoft.validation.core.algorithm.country.sv.ElSalvadorNitValidator;
import com.ksoft.validation.core.algorithm.country.uy.UruguayCiValidator;
import com.ksoft.validation.core.algorithm.country.uy.UruguayRutValidator;
import com.ksoft.validation.core.algorithm.country.ve.VenezuelaCiValidator;
import com.ksoft.validation.core.algorithm.country.ve.VenezuelaRifValidator;

import java.util.EnumMap;
import java.util.Map;

public class IdValidationServiceImpl implements IdValidationService {
    private final Map<Country, Map<DocumentType, DocumentValidator>> validatorsRegistry;
    private static final IdValidationService INSTANCE = new IdValidationServiceImpl();

    private IdValidationServiceImpl() {
        this.validatorsRegistry = new EnumMap<>(Country.class);
        initializeValidators();
    }

    public static IdValidationService getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean validate(Country country, DocumentType type, String documentNumber) {
        // Implementación de validación
        if (country == null || type == null || documentNumber == null) {
            return false;
        }

        Map<DocumentType, DocumentValidator> countryValidators = validatorsRegistry.get(country);
        if (countryValidators == null)
            return false;

        DocumentValidator validator = countryValidators.get(type);
        if (validator == null)
            return false;

        return validator.isValid(documentNumber);
    }

    private void register(Country country, DocumentType type, DocumentValidator validator) {
        validatorsRegistry.computeIfAbsent(country, k -> new EnumMap<>(DocumentType.class))
                .put(type, validator);
    }

    private void initializeValidators() {
        // Argentina:
        register(Country.ARGENTINA, DocumentType.CUIT, new ArgentinaCuitCuilValidator());
        register(Country.ARGENTINA, DocumentType.CUIL, new ArgentinaCuitCuilValidator());
        register(Country.ARGENTINA, DocumentType.DNI_AR, new ArgentinaDniValidator());
        // Bolivia
        register(Country.BOLIVIA, DocumentType.CI_BO, new BoliviaCiValidator());
        register(Country.BOLIVIA, DocumentType.NIT_BO, new BoliviaNitValidator());
        // Brasil
        register(Country.BRASIL, DocumentType.CPF, new BrazilCpfValidator());
        register(Country.BRASIL, DocumentType.CNPJ, new BrazilCnpjValidator());
        register(Country.BRASIL, DocumentType.RG_BR, new BrazilRgValidator());
        // Chile
        register(Country.CHILE, DocumentType.RUT_CL, new ChileRutValidator());
        register(Country.CHILE, DocumentType.CI_CL, new ChileCiValidator());
        register(Country.CHILE, DocumentType.CEDULA_RESIDENCIA_CL, new ChileCiValidator());
        // Colombia
        register(Country.COLOMBIA, DocumentType.CC, new ColombiaCcValidator());
        register(Country.COLOMBIA, DocumentType.CE_CO, new ColombiaCeValidator());
        register(Country.COLOMBIA, DocumentType.TI_CO, new ColombiaTiValidator());
        register(Country.COLOMBIA, DocumentType.NIT_CO, new ColombiaNitValidator());
        // Costa Rica
        register(Country.COSTA_RICA, DocumentType.CI_CR, new CostaRicaCrValidator());
        register(Country.COSTA_RICA, DocumentType.DIMEX_CR, new CostaRicaDimexValidator());
        // Ecuador
        register(Country.ECUADOR, DocumentType.CI_EC, new EcuadorCiValidator());
        register(Country.ECUADOR, DocumentType.RUC_EC, new EcuadorRucValidator());
        // El Salvador
        register(Country.EL_SALVADOR, DocumentType.DUI_SV, new ElSalvadorDuiValidator());
        register(Country.EL_SALVADOR, DocumentType.NIT_SV, new ElSalvadorNitValidator());
        // Guatemala
        register(Country.GUATEMALA, DocumentType.DPI_GT, new GuatemalaDpiValidator());
        register(Country.GUATEMALA, DocumentType.NIT_GT, new GuatemalaNitValidator());
        // Honduras
        register(Country.HONDURAS, DocumentType.RTN_HN, new HondurasRtnValidator());
        // México
        register(Country.MEXICO, DocumentType.CURP, new MexicoCurpValidator());
        register(Country.MEXICO, DocumentType.RFC, new MexicoRfcValidator());
        // Nicaragua
        register(Country.NICARAGUA, DocumentType.CI_NI, new NicaraguaCiValidator());
        register(Country.NICARAGUA, DocumentType.NIT_NI, new NicaraguaNitValidator());
        // Panamá
        register(Country.PANAMA, DocumentType.CIP_PA, new PanamaCipValidator());
        register(Country.PANAMA, DocumentType.RUC_PA, new PanamaRucValidator());
        // Paraguay
        register(Country.PARAGUAY, DocumentType.CI_PY, new ParaguayCiValidator());
        register(Country.PARAGUAY, DocumentType.RUC_PY, new ParaguayRucValidator());
        // Perú
        register(Country.PERU, DocumentType.DNI_PE, new PeruDniValidator());
        register(Country.PERU, DocumentType.RUC_PE, new PeruRucValidator());
        // Puerto Rico
        register(Country.PUERTO_RICO, DocumentType.LICENSE_PR, new PuertoRicoLicenseValidator());
        // República Dominicana
        register(Country.REPUBLICA_DOMINICANA, DocumentType.CIE_DO, new DominicanCieValidator());
        register(Country.REPUBLICA_DOMINICANA, DocumentType.RNC_DO, new DominicanRncValidator());
        // Uruguay
        register(Country.URUGUAY, DocumentType.CI_UY, new UruguayCiValidator());
        register(Country.URUGUAY, DocumentType.RUT_UY, new UruguayRutValidator());
        // Venezuela
        register(Country.VENEZUELA, DocumentType.CI_VE, new VenezuelaCiValidator());
        register(Country.VENEZUELA, DocumentType.RIF_VE, new VenezuelaRifValidator());
    }
}