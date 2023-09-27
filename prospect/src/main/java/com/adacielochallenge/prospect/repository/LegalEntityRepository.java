package com.adacielochallenge.prospect.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.adacielochallenge.prospect.model.LegalEntity;

public interface LegalEntityRepository extends ListCrudRepository<LegalEntity, Long> {
    List<LegalEntity> findByCnpj(String cnpj);

    boolean existsByCnpj(String cnpj);
}
