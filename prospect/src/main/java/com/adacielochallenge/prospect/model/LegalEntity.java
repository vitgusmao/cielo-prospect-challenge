package com.adacielochallenge.prospect.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("LEGAL_ENTITY")
@ToString
@Getter
public class LegalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Setter
    private String cnpj;

    @Setter
    private String corporateReason;

    @Setter
    private int mcc;

    // Verificar se a pessoa física de contato da uma
    // empresa poderia ser ou não uma chave externa para a tabela de pessoas físicas

    @Setter
    private String contactCPF;

    @Setter
    private String contactName;

    @Setter
    private String contactEmail;

}
