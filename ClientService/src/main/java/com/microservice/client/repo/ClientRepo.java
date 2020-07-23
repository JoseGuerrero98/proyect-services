package com.microservice.client.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.microservice.client.model.Client;

@Repository
public interface ClientRepo extends ReactiveMongoRepository<Client, String> {

}
