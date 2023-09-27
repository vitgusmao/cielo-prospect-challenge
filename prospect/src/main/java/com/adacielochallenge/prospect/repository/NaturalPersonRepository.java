package com.adacielochallenge.prospect.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.adacielochallenge.prospect.model.NaturalPerson;

public interface NaturalPersonRepository extends CrudRepository<NaturalPerson, Long> {
    List<NaturalPerson> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
