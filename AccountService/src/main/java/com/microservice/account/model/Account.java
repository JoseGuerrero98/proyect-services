package com.microservice.account.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Account")
public class Account {
	
	@Id
	private String id;
	
	private String typeaccount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeaccount() {
		return typeaccount;
	}

	public void setTypeaccount(String typeaccount) {
		this.typeaccount = typeaccount;
	}
	
}
