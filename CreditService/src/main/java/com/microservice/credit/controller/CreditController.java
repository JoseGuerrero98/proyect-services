package com.microservice.credit.controller;

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

import com.microservice.credit.model.Credit;
import com.microservice.credit.service.CreditService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/credit")
public class CreditController {
	
	@Autowired
	private CreditService service;
	
	@GetMapping("/all")
	public Flux<Credit> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/findby/{id}")
	public Mono<Credit> findById(@PathVariable("id") String id) {
		return service.findById(id);
	}
	
	@PostMapping("/create")
	public Mono<ResponseEntity<Credit>> createCredit(@Validated @RequestBody Credit credit) {
		return service.createCredit(credit)
				.map(item -> 
				ResponseEntity.created(URI.create("/credit".concat(item.getId())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(item)
						);
	}
	
	@PutMapping("/update/{id}")
	public Mono<ResponseEntity<Credit>> updateCredit(@PathVariable("id") String id, @RequestBody Credit credit) {
		return service.updateCredit(credit, id)
				.map(item -> 
				ResponseEntity.created(URI.create("/credit".concat(item.getId())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(item)
						);
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<ResponseEntity<Void>> deleteCredit(@PathVariable("id") String id) {
		return service.deleteCredit(id)
				.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
}
