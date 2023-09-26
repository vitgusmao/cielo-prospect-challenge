package com.adacielochallenge.prospect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class LegalEntity {
    @Id
    @GeneratedValue()
    private long id;

    private String cnpj; // número de 14 dígitos formatado com zeros à esquerda
    private String corporateReason; // máximo de 50 caracteres
    private int mcc; // “Merchant Category Code“: número com no máximo 4 caracteres

    private String contactCPF; // número de 11 dígitos formatado com zeros à esquerda
    private String contactName; // máximo de 50 caracteres
    private String contactEmail; // expressão regular para
                                 // validação:"^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-.]+)\.([a-zA-Z]{2,5})$")

}
