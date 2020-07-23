package com.example.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.client.model.Client;
import com.example.client.repo.ClientRepo;
import com.example.client.service.ClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepo repo;
	
	@Override
	public Flux<Client> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	@Override
	public Mono<Client> createClient(Client client) {
		return repo.save(client);
	}
	
	@Override
	public Mono<Void> deleteClient(String id) {
		try {
			return repo.findById(id).flatMap(acc -> {
				return repo.delete(acc);
			});
		} catch (Exception e) {
			// TODO: handle exception
			return Mono.error(e);
		}
	}
	
	@Override
	public Mono<Client> findById(String id) {
		return repo.findById(id);
	}
	
	@Override
	public Mono<Client> updateClient(Client client, String id) {
		return repo.findById(id).flatMap(acc -> {
			acc.setName(client.getName());
			acc.setTypeclient(client.getTypeclient());
			acc.setDni(client.getDni());
			acc.setRuc(client.getRuc());
			return repo.save(acc);
		}).switchIfEmpty(Mono.empty());
	}
	
}
