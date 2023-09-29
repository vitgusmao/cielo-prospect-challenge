package com.adacielochallenge.prospect.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
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
import com.adacielochallenge.prospect.model.LegalEntity;
import com.adacielochallenge.prospect.model.NaturalPerson;
import com.adacielochallenge.prospect.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping
public class ClientController {
    private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "create a new legal entity prospect")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successfully created a new legal entity prospect"),
            @ApiResponse(responseCode = "400", description = "client already exists or data provided is not valid."),
            @ApiResponse(responseCode = "500", description = "unexpected error creating a new client prospect.")
    })
    @PostMapping("/legal-entities")
    @Validated
    public ResponseEntity<Object> createLegalEntity(@Valid @RequestBody LegalEntityCreateDTO legalEntityCreateDTO) {
        LOG.debug("create request arrived: %s".formatted(legalEntityCreateDTO.toString()));
        try {
            LegalEntity client = clientService.createLegalEntity(legalEntityCreateDTO);
            return new ResponseEntity<>(client, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .badRequest()
                    .body(this.generateErrorMessage("cliente já existe."));
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(this.generateErrorMessage("erro na criação do prospect do cliente."));
        }
    }

    @Operation(summary = "create a new natural person prospect")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successfully created a new natural person prospect"),
            @ApiResponse(responseCode = "400", description = "client already exists or data provided is not valid."),
            @ApiResponse(responseCode = "500", description = "unexpected error creating a new client prospect.")
    })
    @PostMapping("/natural-persons")
    @Validated
    public ResponseEntity<Object> createNaturalPerson(
            @Valid @RequestBody NaturalPersonCreateDTO naturalPersonCreateDTO) {

        LOG.info("create request arrived: %s".formatted(naturalPersonCreateDTO.toString()));
        try {
            NaturalPerson client = clientService.createNaturalPerson(naturalPersonCreateDTO);
            return new ResponseEntity<>(client, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest()
                    .body(this.generateErrorMessage("cliente já existe."));
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResponseEntity.internalServerError()
                    .body(this.generateErrorMessage("erro na criação do prospect do cliente."));
        }
    }

    @Operation(summary = "get the list off all the clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully retrieved all clients"),
            @ApiResponse(responseCode = "500", description = "unexpected error retrieving clients list.")
    })
    @GetMapping("/clients")
    public ResponseEntity<Object> list() {

        try {
            List<Client> clientList = clientService.listClients();
            return ResponseEntity.ok(clientList);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(this.generateErrorMessage("erro ao ler lista de clientes"));
        }

    }

    @Operation(summary = "retrieve one detailed client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully retrieved client by path variable {id}."),
            @ApiResponse(responseCode = "404", description = "client not found."),
            @ApiResponse(responseCode = "500", description = "unexpected error.")
    })
    @GetMapping("/clients/{id}")
    public ResponseEntity<Object> retrieve(@PathVariable Long id) {
        try {
            Client client = clientService.retrieveClient(id);
            return ResponseEntity.ok().body(client);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(this.generateErrorMessage("ocorreu um erro inesperado."));
        }
    }

    @Operation(summary = "update one client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully updated prospect."),
            @ApiResponse(responseCode = "400", description = "malformed request body."),
            @ApiResponse(responseCode = "404", description = "client not found."),
            @ApiResponse(responseCode = "500", description = "unexpected error.")
    })
    @PutMapping("/clients/{id}")
    @Validated
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody ClientUpdateDTO clientUpdateDTO) {
        try {
            Client client = clientService.updateClient(id, clientUpdateDTO);
            return ResponseEntity.ok().body(client);

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(this.generateErrorMessage("ocorreu um erro inesperado."));
        }
    }

    @Operation(summary = "delete one client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully deleted prospect."),
            @ApiResponse(responseCode = "500", description = "unexpected error.")
    })
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return ResponseEntity.ok(this.generateErrorMessage("successfully deleted prospect."));

        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(this.generateErrorMessage("ocorreu um erro inesperado."));
        }
    }

    private Object generateErrorMessage(String message) {
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("message", message);
        return jsonNode;
    }

}
