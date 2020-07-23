package com.microservice.credit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.credit.model.Credit;
import com.microservice.credit.repo.CreditRepo;
import com.microservice.credit.service.CreditService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditService {
	
	@Autowired
	private CreditRepo repo;
	
	@Override
	public Flux<Credit> findAll() {
		return repo.findAll();
	}
	
	@Override
	public Mono<Credit> findById(String id) {
		return repo.findById(id);
	}
	
	@Override
	public Mono<Credit> createCredit(Credit credit) {
		return repo.save(credit);
	}
	
	@Override
	public Mono<Credit> updateCredit(Credit credit, String id) {
		return repo.findById(id).flatMap(acc -> {
			acc.setTypecredit(credit.getTypecredit());
			return repo.save(acc);
		}).switchIfEmpty(Mono.empty());
	}
	
	@Override
	public Mono<Void> deleteCredit(String id) {
		try {
			return repo.findById(id).flatMap(acc -> {
				return repo.delete(acc);
			});
		} catch (Exception e) {
			// TODO: handle exception
			return Mono.error(e);
		}
	}
	
}
