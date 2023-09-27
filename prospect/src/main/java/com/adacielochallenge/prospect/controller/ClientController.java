package com.adacielochallenge.prospect.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adacielochallenge.prospect.dto.ClientCreateDTO;
import com.adacielochallenge.prospect.model.Client;
import com.adacielochallenge.prospect.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/prospects")
public class ClientController {
    private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Create a new prospect")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created a new prospect")
    })
    @PostMapping
    @Validated
    public ResponseEntity<String> create(@Valid @RequestBody ClientCreateDTO clientCreateDTO) {
        LOG.debug("create request arrived: %s".formatted(clientCreateDTO.toString()));
        try {
            clientService.createClient(clientCreateDTO);
            return ResponseEntity.ok("successfully created a new client pre registration.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("client pre registration already exists.");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResponseEntity.internalServerError().body("error creating a new client pre registration.");
        }
    }

    @Operation(summary = "Get the list off prospects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all prospects")
    })
    @GetMapping
    public ResponseEntity<String> list() {

        try {
            List<Client> clientList = clientService.listClients();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(clientList);

            return ResponseEntity.ok(jsonString);
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage());
            return ResponseEntity.internalServerError().body("Erro ao ler lista de clientes");
        }

    }

    // @GetMapping("{id}")
    // public void retrieve() {
    // }

    // @PutMapping("{id}")
    // public void update() {
    // }

    // @DeleteMapping("{id}")
    // public void delete() {
    // }

}
