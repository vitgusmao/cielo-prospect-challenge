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
public class LegalEntityCreateDTO extends ClientCreateDTO {

    @Pattern(regexp = "^[0-9]{14}$", message = "CNPJ must have 14 digits")
    @NotNull
    private String cnpj;

    @Size(max = 50, message = "corporate reason must not exceed 50 characters")
    @NotNull
    private String corporateReason;

}