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
public class NaturalPersonUpdateDTO {

    @Max(value = 9999, message = "'Merchant Category Code' must not exceed 4 digits")
    private int mcc;

    @Size(max = 50, message = "name must not exceed 50 characters")
    private String name;

    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$", message = "email must be a valid e-mail")
    private String email;

}