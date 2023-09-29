package com.adacielochallenge.prospect.dto;

import com.adacielochallenge.prospect.model.Client;
import com.adacielochallenge.prospect.model.LegalEntity;
import com.adacielochallenge.prospect.model.NaturalPerson;

import jakarta.validation.ValidationException;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientUpdateDTO {

    @Size(max = 4, min = 1, message = "'Merchant Category Code' não deve exceder 4 caracteres.")
    @Pattern(regexp = "^[0-9]*$", message = "'Merchant Category Code' só pode assumir valores numéricos.")
    private String mcc;

    @Size(max = 11, min = 11, message = "cpf deve conter exatamente 11 dígitos.")
    @Pattern(regexp = "^[0-9]{11}$", message = "cpf só pode assumir valores numéricos")
    private String cpf;

    @Size(max = 50, message = "nome não pode ter mais de 50 caracteres")
    private String name;

    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$", message = "e-mail deve ser um e-mail válido")
    private String email;

    @Size(max = 50, message = "razão social não pode ter mais de 50 caracteres")
    private String corporateReason;

    public boolean validate(Client client) {
        if (client instanceof NaturalPerson) {
            boolean noLegalEntityFields = this.corporateReason == null;
            boolean noUniqueNaturalPersonFields = this.cpf == null;
            return noLegalEntityFields && noUniqueNaturalPersonFields;
        } else if (client instanceof LegalEntity) {
            return true;
        } else {
            throw new ValidationException("invalid client instance");
        }
    }
}