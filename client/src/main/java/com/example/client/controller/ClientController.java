package com.example.client.controller;

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

import com.example.client.model.Client;
import com.example.client.service.ClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private ClientService clientservice;
	
	@GetMapping("/all")
	public Flux<Client> findAll(){
		return clientservice.findAll();
	}
	
	@GetMapping("/findby/{id}")
	public Mono<Client> findById(@PathVariable String id) {
		return clientservice.findById(id);
	}
	
	@PostMapping("/create")
	public Mono<ResponseEntity<Client>> createClient(@Validated @RequestBody Client client) {
		return clientservice.createClient(client)
				.map(item -> 
				ResponseEntity.created(URI.create("/client".concat(item.getId())))
				.contentType(MediaType.APPLICATION_JSON).body(item));
	}
	
	@PutMapping("/update/{id}")
	public Mono<ResponseEntity<Client>> updateClient(@PathVariable("id") String id, @RequestBody Client client) {
		return clientservice.updateClient(client, id)
				.map(c -> ResponseEntity.created(URI.create("/client".concat(c.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(c));
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<ResponseEntity<Void>> deleteClient(@PathVariable String id) {
		return clientservice.deleteClient(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
	
	
}
