package com.adacielochallenge.prospect.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adacielochallenge.prospect.model.Client;
import com.adacielochallenge.prospect.service.ProspectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class ProspectController {

    private static final Logger LOG = LoggerFactory.getLogger(ProspectController.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // @MessageMapping("/prospects")
    // @SendTo("/topic/something")
    // public void prospectQueue() {

    // }

    private final ProspectService prospectService;

    @Autowired
    public ProspectController(ProspectService prospectService) {
        this.prospectService = prospectService;
    }

    @Operation(summary = "retrieve one prospect from the queue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully shifted and returned the first prospect from the queue")
    })
    @PostMapping("/prospects")
    @Validated
    public ResponseEntity<String> shiftProspects() {
        try {
            Client prospect = prospectService.shiftProspects();
            String stringProspect = objectMapper.writeValueAsString(prospect);
            LOG.info("prospect de id '%d' removido da fila com sucesso.".formatted(prospect.getId()));
            return ResponseEntity.ok(stringProspect);
        } catch (IllegalStateException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage());
            return ResponseEntity.internalServerError().body("erro inesperado no servidor.");
        }
    }

}
