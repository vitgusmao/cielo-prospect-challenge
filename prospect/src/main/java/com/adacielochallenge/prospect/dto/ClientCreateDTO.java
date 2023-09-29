package com.adacielochallenge.prospect.dto;

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

    @NotNull(message = "'Merchant Category Code' não pode ser vazio.")
    @Size(max = 4, min = 1, message = "'Merchant Category Code' não deve exceder 4 caracteres.")
    @Pattern(regexp = "^[0-9]*$", message = "'Merchant Category Code' só pode assumir valores numéricos.")
    private String mcc;

    @NotNull(message = "cpf não pode ser vazio.")
    @Size(max = 11, min = 11, message = "cpf deve conter exatamente 11 dígitos.")
    @Pattern(regexp = "^[0-9]*$", message = "cpf só pode assumir valores numéricos")
    private String cpf;

    @NotNull(message = "nome não pode ser vazio.")
    @Size(max = 50, message = "nome não pode ter mais de 50 caracteres")
    private String name;

    @NotNull(message = "e-mail não pode ser vazio.")
    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$", message = "e-mail deve ser um e-mail válido")
    private String email;
}