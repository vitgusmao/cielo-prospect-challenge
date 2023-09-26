package com.adacielochallenge.prospect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class LegalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 14, min = 14, message = "CNPJ must have 14 digits")
    private String cnpj; // número de 14 dígitos formatado com zeros à esquerda

    @Size(max = 50, message = "Corporate Reason must not exceed 50 characters")
    private String corporateReason; // máximo de 50 caracteres

    @Max(value = 9999, message = "'Merchant Category Code' must not exceed 4 digits")
    private int mcc; // “Merchant Category Code“: número com no máximo 4 caracteres

    // Verificar se a pessoa física de contato da uma
    // empresa poderia ser ou não uma chave externa para a tabela de pessoas físicas

    @Size(max = 11, min = 11, message = "Contact's CPF must have 11 digits")
    private String contactCPF; // número de 11 dígitos formatado com zeros à esquerda

    @Size(max = 50, message = "Contact's name must not exceed 50 characters")
    private String contactName; // máximo de 50 caracteres

    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$", message = "Contact's e-mail must be a valid e-mail")
    private String contactEmail; // expressão regular para
                                 // validação: "^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-.]+)\.([a-zA-Z]{2,5})$"

}
