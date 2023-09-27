package com.adacielochallenge.prospect.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.adacielochallenge.prospect.model.LegalEntity;

public interface LegalEntityRepository extends CrudRepository<LegalEntity, Long> {
    List<LegalEntity> findByCnpj(String cnpj);

    boolean existsByCnpj(String cnpj);
}
