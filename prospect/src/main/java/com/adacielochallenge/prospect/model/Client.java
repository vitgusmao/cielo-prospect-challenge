package com.adacielochallenge.prospect.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@ToString
@Getter
@DiscriminatorColumn(name = "client_type", discriminatorType = DiscriminatorType.STRING)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "client")
    @Setter
    private NaturalPerson naturalPerson;

    @OneToOne(mappedBy = "client")
    @Setter
    private LegalEntity legalEntity;
}
