package com.microservice.account.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.microservice.account.model.Account;

@Repository
public interface AccountRepo extends ReactiveMongoRepository<Account, String> {

}
