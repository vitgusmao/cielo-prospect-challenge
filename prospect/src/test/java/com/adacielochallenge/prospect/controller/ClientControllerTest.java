package com.adacielochallenge.prospect.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateNaturalPerson() throws Exception {
        String requestBody = "{" +
                " \"mcc\": \"5042\"," +
                " \"cpf\": \"52554559410\"," +
                " \"name\": \"string\"," +
                " \"email\": \"teste@example.com.br\"" +
                " }";

        mockMvc.perform(MockMvcRequestBuilders.post("/natural-persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content()
                        .json("{\"message\": \"successfully created a new client prospect.\"}"));
    }

    @Test
    public void testCreateLegalEntity() throws Exception {
        String requestBody = "{" +
                " \"mcc\": \"9755\"," +
                " \"cpf\": \"30281417633\"," +
                " \"name\": \"Contact Name\"," +
                " \"email\": \"teste@example.com.br\"," +
                " \"cnpj\": \"80178708582649\"," +
                " \"corporateReason\": \"Empresa X\"" +
                " }";

        mockMvc.perform(MockMvcRequestBuilders.post("/legal-entities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content()
                        .json("{\"message\": \"successfully created a new client prospect.\"}"));
    }
}