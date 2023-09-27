package com.adacielochallenge.prospect.model;

import java.util.Date;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@ToString
@Getter
@DiscriminatorColumn(name = "client_type", discriminatorType = DiscriminatorType.STRING)
// TODO: Testar para melhor escalabilidade
// discriminatorType = DiscriminatorType.INTEGER,
// name = "client_type_id",
// columnDefinition = "TINYINT(1)"
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = new Date();

}
