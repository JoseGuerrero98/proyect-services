package com.microservice.credit.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.microservice.credit.model.Credit;

@Repository
public interface CreditRepo extends ReactiveMongoRepository<Credit, String> {

}
