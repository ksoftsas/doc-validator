package com.ksoft.validator.api.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationResponse {
    private boolean valid;
    private String formattedDocument;
    private String documentType;
    private String additionalInfo;
    
}