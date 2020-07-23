package com.microservice.credit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Credit")
public class Credit {
	
	@Id
	private String id;
	
	private String typecredit;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypecredit() {
		return typecredit;
	}

	public void setTypecredit(String typecredit) {
		this.typecredit = typecredit;
	}
	
}
