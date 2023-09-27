package com.adacielochallenge.prospect.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("NATURAL_PERSON")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "CPF" }))
@ToString
@Getter
public class NaturalPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Setter
    private String cpf;

    @Setter
    private int mcc;

    @Setter
    private String name;

    @Setter
    private String email;
}
