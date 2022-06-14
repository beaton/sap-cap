package com.top.cap.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping
public class CatalogService {
	
	// Maps to http://localhost:8080/catalog
	@RequestMapping(value = "/catalog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getBooks() {
		return ResponseEntity.status(200).body("Hello world");		
	}
}