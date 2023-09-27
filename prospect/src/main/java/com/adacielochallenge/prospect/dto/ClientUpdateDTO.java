package com.adacielochallenge.prospect.dto;

import com.adacielochallenge.prospect.model.Client;
import com.adacielochallenge.prospect.model.LegalEntity;
import com.adacielochallenge.prospect.model.NaturalPerson;

import jakarta.annotation.Nullable;
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

    @Pattern(regexp = "^[0-9]{4}$", message = "'Merchant Category Code' must not exceed 4 digits")
    @Nullable
    private String mcc;

    @Pattern(regexp = "^[0-9]{11}$", message = "cpf must have 11 digits")
    @Nullable
    private String cpf;

    @Size(max = 50, message = "name must not exceed 50 characters")
    @Nullable
    private String name;

    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$", message = "email must be a valid e-mail")
    @Nullable
    private String email;

    @Size(max = 50, message = "corporate reason must not exceed 50 characters")
    @Nullable
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