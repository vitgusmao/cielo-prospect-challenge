package com.adacielochallenge.prospect.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.adacielochallenge.prospect.model.Client;
import com.adacielochallenge.prospect.model.ProspectStatus;

public interface ClientRepository extends ListCrudRepository<Client, Long> {
    List<Client> findByStatus(ProspectStatus status);
}
