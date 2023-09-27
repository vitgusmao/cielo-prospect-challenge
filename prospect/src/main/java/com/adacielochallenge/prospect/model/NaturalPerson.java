package com.adacielochallenge.prospect.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("NATURAL_PERSON")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "ID", "CPF" }))
@ToString
@Getter
public class NaturalPerson extends Client {

    @Setter
    private String cpf;

    @Setter
    private int mcc;

    @Setter
    private String name;

    @Setter
    private String email;
}
