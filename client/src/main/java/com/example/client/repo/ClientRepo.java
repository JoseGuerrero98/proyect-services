package com.example.client.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Client;

@Repository
public interface ClientRepo extends ReactiveMongoRepository<Client, String> {
	
}
