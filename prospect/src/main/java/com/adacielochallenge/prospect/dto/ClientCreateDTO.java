package com.adacielochallenge.prospect.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class ClientCreateDTO {

    @Max(value = 9999, message = "'Merchant Category Code' must not exceed 4 digits")
    @NotNull
    private int mcc;

    @Pattern(regexp = "^[0-9]{11}$", message = "cpf must have 11 digits")
    @NotNull
    private String cpf;

    @Size(max = 50, message = "Name must not exceed 50 characters")
    @NotNull
    private String name;

    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$", message = "email must be a valid e-mail")
    @NotNull
    private String email;
}