package com.ksoft.validator.api.controllers;

import com.ksoft.validator.api.dtos.ValidationRequest;
import com.ksoft.validator.api.dtos.ValidationResponse;
import com.ksoft.validator.core.services.DocumentValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/validate")
public class ValidationController {

    private final DocumentValidationService validationService;

    public ValidationController(DocumentValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping
    public ResponseEntity<ValidationResponse> validateDocument(
            @Valid @RequestBody ValidationRequest request) {
        ValidationResponse response = validationService.validateDocument(
            request.getCountryCode(), 
            request.getDocumentType(), 
            request.getDocumentNumber());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{countryCode}/{documentType}/{documentNumber}")
    public ResponseEntity<ValidationResponse> validateDocument(
            @PathVariable String countryCode,
            @PathVariable String documentType,
            @PathVariable String documentNumber) {
        ValidationResponse response = validationService.validateDocument(
            countryCode, 
            documentType, 
            documentNumber);
        
        return ResponseEntity.ok(response);
    }
}