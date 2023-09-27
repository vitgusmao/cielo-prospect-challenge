package com.adacielochallenge.prospect.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adacielochallenge.prospect.dto.ClientUpdateDTO;
import com.adacielochallenge.prospect.dto.LegalEntityCreateDTO;
import com.adacielochallenge.prospect.dto.NaturalPersonCreateDTO;
import com.adacielochallenge.prospect.model.Client;
import com.adacielochallenge.prospect.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping
public class ClientController {
    private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "create a new legal entity prospect")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully created a new legal entity prospect")
    })
    @PostMapping("/legal-entities")
    @Validated
    public ResponseEntity<String> createLegalEntity(@Valid @RequestBody LegalEntityCreateDTO legalEntityCreateDTO) {
        LOG.debug("create request arrived: %s".formatted(legalEntityCreateDTO.toString()));
        try {
            clientService.createLegalEntity(legalEntityCreateDTO);
            return ResponseEntity.ok("successfully created a new client prospect.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("client prospect already exists.");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResponseEntity.internalServerError().body("error creating a new client pre registration.");
        }
    }

    @Operation(summary = "create a new natural person prospect")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully created a new prospect")
    })
    @PostMapping("/natural-persons")
    @Validated
    public ResponseEntity<String> createNaturalPerson(
            @Valid @RequestBody NaturalPersonCreateDTO naturalPersonCreateDTO) {
        LOG.debug("create request arrived: %s".formatted(naturalPersonCreateDTO.toString()));
        try {
            clientService.createNaturalPerson(naturalPersonCreateDTO);
            return ResponseEntity.ok("successfully created a new client prospect.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("client prospect already exists.");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResponseEntity.internalServerError().body("error creating a new client prospect.");
        }
    }

    @Operation(summary = "Get the list off prospects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully retrieved all prospects")
    })
    @GetMapping("/prospects")
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

    @Operation(summary = "retrieve one detailed prospect")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully retrieved prospect by path variable {id}")
    })
    @GetMapping("/prospects/{id}")
    public ResponseEntity<String> retrieve(@PathVariable Long id) {
        try {
            Client client = clientService.retrieveClient(id);
            ObjectMapper objectMapper = new ObjectMapper();
            return ResponseEntity.ok().body(objectMapper.writeValueAsString(client));
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage());
            return ResponseEntity.internalServerError().body("erro processar retorno do cliente");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResponseEntity.internalServerError().body("Ocorreu um erro inesperado.");
        }
    }

    @Operation(summary = "Update one prospect")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully updated prospect.")
    })
    @PutMapping("/prospects/{id}")
    @Validated
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody ClientUpdateDTO clientUpdateDTO) {
        try {
            Client client = clientService.updateClient(id, clientUpdateDTO);
            if (client != null) {
                return ResponseEntity.badRequest().body("corpo da requisição inválido");
            }
            return ResponseEntity.ok().body("prospect atualizado com sucesso");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResponseEntity.internalServerError().body("ocorreu um erro inesperado.");
        }
    }

    @Operation(summary = "Delete one prospect")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully deleted prospect.")
    })
    @DeleteMapping("/prospects/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return ResponseEntity.ok("successfully deleted prospect.");

        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResponseEntity.internalServerError().body("ocorreu um erro inesperado.");
        }
    }

}
