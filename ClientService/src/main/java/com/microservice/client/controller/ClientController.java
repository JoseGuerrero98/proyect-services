package com.microservice.client.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.client.model.Client;
import com.microservice.client.service.ClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@GetMapping("/all")
	public Flux<Client> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/findby/{id}")
	public Mono<Client> findById(@PathVariable("id") String id) {
		return service.findById(id);
	}
	
	@PostMapping("/create")
	public Mono<ResponseEntity<Client>> createClient(@Validated @RequestBody Client client) {
		return service.createClient(client)
				.map(item ->
				ResponseEntity.created(URI.create("/client".concat(item.getId())))
				.contentType(MediaType.APPLICATION_JSON).body(item)
						);
	}
	
	@PutMapping("/update/{id}")
	public Mono<ResponseEntity<Client>> updateClient(@PathVariable("id") String id, @RequestBody Client client) {
		return service.updateClient(client, id)
				.map(item -> 
				ResponseEntity.created(URI.create("/client".concat(item.getId())))
				.contentType(MediaType.APPLICATION_JSON).body(item)
						);
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<ResponseEntity<Void>> deleteClient(@PathVariable("id") String id) {
		return service.deleteClient(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
}
