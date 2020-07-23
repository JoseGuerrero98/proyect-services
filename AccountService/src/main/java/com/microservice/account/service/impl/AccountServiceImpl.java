package com.microservice.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.account.model.Account;
import com.microservice.account.repo.AccountRepo;
import com.microservice.account.service.AccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepo repo;
	
	public Flux<Account> findAll() {
		return repo.findAll();
	}
	
	@Override
	public Mono<Account> findById(String id) {
		return repo.findById(id);
	}
	
	@Override
	public Mono<Account> createAccount(Account account) {
		return repo.save(account);
	}
	
	@Override
	public Mono<Account> updateAccount(Account account, String id) {
		return repo.findById(id).flatMap(acc -> {
			acc.setTypeaccount(account.getTypeaccount());
			return repo.save(acc);
		}).switchIfEmpty(Mono.empty());
	}
	
	@Override
	public Mono<Void> deleteAccount(String id) {
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
