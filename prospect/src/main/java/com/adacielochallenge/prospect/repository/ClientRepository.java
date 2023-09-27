package com.adacielochallenge.prospect.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.adacielochallenge.prospect.model.Client;

public interface ClientRepository extends ListCrudRepository<Client, Long> {
}
