package com.adacielochallenge.prospect.repository;

import org.springframework.data.repository.CrudRepository;

import com.adacielochallenge.prospect.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
