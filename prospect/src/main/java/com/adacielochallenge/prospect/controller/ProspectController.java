package com.adacielochallenge.prospect.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adacielochallenge.prospect.dto.ProspectUpdateDTO;
import com.adacielochallenge.prospect.model.Client;
import com.adacielochallenge.prospect.service.ProspectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

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
            @ApiResponse(responseCode = "200", description = "successfully shifted and returned the first prospect from the queue.")
    })
    @PostMapping("/prospects")
    @Validated
    public ResponseEntity<Object> shiftProspects() {
        try {
            Client prospect = prospectService.shiftProspects();
            LOG.info("prospect de id '%d' removido da fila com sucesso.".formatted(prospect.getId()));
            return ResponseEntity.ok(prospect);
        } catch (IllegalStateException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.badRequest().body(this.generateErrorMessage(e.getMessage()));
        }
    }

    @Operation(summary = "update prospect status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully updated prospect.")
    })
    @PutMapping("/prospects/{id}")
    @Validated
    public ResponseEntity<Object> updateProspect(
            @PathVariable Long id,
            @Valid @RequestBody ProspectUpdateDTO prospectUpdateDTO) {
        try {
            Client prospect = prospectService.updateProspect(id, prospectUpdateDTO);
            return ResponseEntity.ok(prospect);
        } catch (IllegalStateException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.badRequest().body(this.generateErrorMessage(e.getMessage()));
        }
    }

    private Object generateErrorMessage(String message) {
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("message", message);
        return jsonNode;
    }

}
