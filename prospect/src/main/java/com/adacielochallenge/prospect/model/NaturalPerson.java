package com.adacielochallenge.prospect.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class NaturalPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 11, min = 11, message = "CPF must have 11 digits")
    private String contactCPF; // número de 11 dígitos formatado com zeros à esquerda

    @Max(value = 9999, message = "'Merchant Category Code' must not exceed 4 digits")
    private int mcc; // “Merchant Category Code“: número com no máximo 4 caracteres

    @Size(max = 50, message = "name must not exceed 50 characters")
    private String name; // máximo de 50 caracteres

    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$", message = "email must be a valid e-mail")
    private String email; // expressão regular para
                          // validação: "^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-.]+)\.([a-zA-Z]{2,5})$"
}
