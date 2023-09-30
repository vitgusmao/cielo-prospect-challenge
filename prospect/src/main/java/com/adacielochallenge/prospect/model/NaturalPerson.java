package com.adacielochallenge.prospect.model;

import com.adacielochallenge.prospect.dto.ClientUpdateDTO;

import jakarta.persistence.Column;
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
    @Column(length = 11)
    private String cpf;

    @Setter
    @Column(length = 4)
    private String mcc;

    @Setter
    @Column(length = 50)
    private String name;

    @Setter
    private String email;

    @Override
    public void update(ClientUpdateDTO updateDTO) {
        String cpf = updateDTO.getCpf();
        String name = updateDTO.getName();
        String email = updateDTO.getEmail();

        if (cpf != null) {
            this.setCpf(cpf);
        } else if (name != null) {
            this.setName(name);
        } else if (email != null) {
            this.setEmail(email);
        }

        super.update(updateDTO);
    }
}
