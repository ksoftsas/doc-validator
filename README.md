# Document Validator API/Library for Latin America

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
[![KSoft S.A.S.](https://img.shields.io/badge/KSoft-SAS?style=for-the-badge&logo=keras&logoColor=%23e01e1e&logoSize=50px&label=KSoft%20SAS&labelColor=white&color=%23e01e1e&link=https%3A%2F%2Fksoftonline.com%2F)](https://ksoftonline.com)

A hybrid solution (library + API) for validating identity documents across Latin American countries, supporting both personal and business identification documents.

## Features

- **Multi-country support**: Validates documents from 18 Latin American countries
- **Multiple document types**: Over 60 different document types supported
- **Hybrid deployment**:
  - **Library**: Use as a Java dependency in your applications
  - **API**: Deploy as a REST service for cross-platform access
- **Advanced validation**:
  - Format validation
  - Check digit algorithms (Modulo 10, Modulo 11, and country-specific variants)
  - Structural validation
- **Easy integration**: Simple API and clear documentation

## Supported Countries and Documents

| Country            | Key Documents Supported                          | Validation Type         |
|--------------------|--------------------------------------------------|-------------------------|
| Argentina          | DNI_AR, CUIL, CUIT                               | Format + Modulo 11      |
| Bolivia            | CI_BO, NIT_BO                                    | Format + Modulo 11      |
| Brazil             | CPF, CNPJ, RG_BR                                 | Format + Modulo 11      |
| Chile              | RUT_CL, CI_CL, CEDULA_RESIDENCIA_CL              | Format + Modulo 11      |
| Colombia           | CC, NIT_CO, CE_CO, TI_CO                         | Format + Modulo 11      |
| Costa Rica         | CI_CR, DIMEX_CR                                  | Format                  |
| Ecuador            | CI_EC, CE_EC, RUC_EC                             | Format + Modulo 10/11   |
| El Salvador        | DUI_SV, NIT_SV                                   | Format + Modulo 10/11   |
| Guatemala          | DPI_GT, NIT_GT                                   | Format + Modulo 11      |
| Honduras           | RTN_HN                                           | Format                  |
| Mexico             | CURP, RFC                                        | Format + Special DV     |
| Nicaragua          | CI_NI, NIT_NI                                    | Format + Modulo 11      |
| Panama             | CIP_PA, RUC_PA                                   | Format + Modulo 11      |
| Paraguay           | CI_PY, RUC_PY                                    | Format + Modulo 11      |
| Peru               | DNI_PE, RUC_PE                                   | Format + Modulo 11      |
| Puerto Rico        | LICENSE_PR                                       | Format                  |
| Dominican Republic | CIE_DO, RNC_DO                                   | Format + Modulo 10/11   |
| Uruguay            | CI_UY, RUT_UY                                    | Format + Modulo 11      |
| Venezuela          | CI_VE, RIF_VE                                    | Format + Modulo 11      |
| Other              | GENERIC_FORMAT, UNKNOWN                          | Format                  |

## Getting Started

### Option 1: Use as a Library

Add the dependency to your Maven project:

```xml
<dependency>
    <groupId>com.ksoft</groupId>
    <artifactId>validator-core</artifactId>
    <version>1.0.0</version>
</dependency>
```

Basic usage:
```java
// Get the validation service instance
IdValidationService validator = IdValidationService.getInstance();

// Validate a document
boolean isValid = validator.validate(Country.ARGENTINA, DocumentType.CUIT, "20-12345678-9");
```

### Option 2: Use the REST API

#### Run the API locally:
```bash
mvn spring-boot:run
```

#### API Endpoints:
- `POST /api/validate`
  ```json
  {
    "country": "ARGENTINA",
    "documentType": "CUIT",
    "documentNumber": "20123456789"
  }
  ```
  
- `GET /api/validate/{country}/{type}/{number}`
  Example: `/api/validate/ARGENTINA/CUIT/20-12345678-9`

#### Sample Response:
```json
{
  "country": "ARGENTINA",
  "documentType": "CUIT",
  "documentNumber": "20123456789",
  "valid": true,
  "message": "Valid CUIT"
}
```

## Project Structure

```
src/
├── main/
│   ├── java/com/ksoft/validation/
│   │   ├── core/          # Core validation logic
│   │   ├── api/           # REST controllers
│   │   ├── model/         # DTOs and entities
│   │   └── util/          # Common utilities
│   └── resources/         # Configuration files
├── test/                  # Unit and integration tests
```

## Contributing

We welcome contributions! Please see our [Contribution Guidelines](CONTRIBUTING.md) for details.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgments

This project is a contribution of **KSoft S.A.S.**  
Website: [https://ksoftonline.com](https://ksoftonline.com)
LinkedIn: [https://www.linkedin.com/company/ksoft](https://www.linkedin.com/company/ksoft)
X: [@ksoftonline](https://x.com/ksoft_sas)

