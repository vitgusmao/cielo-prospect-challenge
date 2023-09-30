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

    @NotNull(message = "cnpj não pode ser vazio.")
    @Size(max = 14, min = 14, message = "cnpj deve conter exatamente 14 dígitos.")
    @Pattern(regexp = "^[0-9]*$", message = "cnpj só pode assumir valores numéricos")
    private String cnpj;

    @NotNull(message = "razão social não pode ser vazio.")
    @Size(max = 50, message = "razão social não pode ter mais de 50 caracteres")
    private String corporateReason;

}