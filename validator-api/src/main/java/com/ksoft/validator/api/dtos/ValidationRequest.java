package com.ksoft.validator.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidationRequest {
    @NotBlank(message = "El código de país es requerido")
    @Pattern(regexp = "[A-Z]{2}", message = "El código de país debe ser de 2 letras mayúsculas")
    private String countryCode;
    
    @NotBlank(message = "El tipo de documento es requerido")
    private String documentType;
    
    @NotBlank(message = "El número de documento es requerido")
    private String documentNumber;
}