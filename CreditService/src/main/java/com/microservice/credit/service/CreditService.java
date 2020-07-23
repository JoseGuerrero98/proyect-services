package com.microservice.credit.service;

import com.microservice.credit.model.Credit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {
	
	public Flux<Credit> findAll();
	public Mono<Credit> findById(String id);
	public Mono<Credit> createCredit(Credit credit);
	public Mono<Credit> updateCredit(Credit credit, String id);
	public Mono<Void> deleteCredit(String id);
	
}
