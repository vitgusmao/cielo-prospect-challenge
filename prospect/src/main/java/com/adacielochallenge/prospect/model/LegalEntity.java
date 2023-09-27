package com.adacielochallenge.prospect.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("LEGAL_ENTITY")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "ID", "CNPJ" }))
@ToString
@Getter
public class LegalEntity extends Client {

    @Setter
    @Column(length = 14)
    private String cnpj;

    @Setter
    @Column(length = 50)
    private String corporateReason;

    @Setter
    @Column(length = 4)
    private String mcc;

    // Verificar se a pessoa física de contato da uma
    // empresa poderia ser ou não uma chave externa para a tabela de pessoas físicas

    @Setter
    @Column(length = 11)
    private String contactCpf;

    @Setter
    @Column(length = 50)
    private String contactName;

    @Setter
    private String contactEmail;

}
