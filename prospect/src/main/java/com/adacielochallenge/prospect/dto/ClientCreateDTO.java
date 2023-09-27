package com.adacielochallenge.prospect.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientCreateDTO {

    private ClientType clientType;

    // common fields
    @Max(value = 9999, message = "'Merchant Category Code' must not exceed 4 digits")
    private int mcc;

    @Pattern(regexp = "^[0-9]{11}$", message = "cpf must have 11 characters")
    private String cpf;

    @Size(max = 50, message = "Name must not exceed 50 characters")
    private String name;

    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$", message = "email must be a valid e-mail")
    private String email;

    // legal entity fields
    @Pattern(regexp = "^[0-9]{14}$", message = "CNPJ must have 14 digits")
    private String cnpj;

    @Size(max = 50, message = "Corporate Reason must not exceed 50 characters")
    private String corporateReason;

    // there is no specific fields for natural person

}