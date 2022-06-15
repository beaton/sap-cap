package com.top.cap.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.top.cap.persistence.BookDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping
public class CatalogController {
	
	@Autowired
	protected BookDAO dao;
	
	// Maps to http://localhost:8080/catalog
	@RequestMapping(value = "/catalog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getBooks() {
				
		// This is a sample so no need for a services 'business' layer.
		String message = "Hello world!";
		try {
			dao.findAll();
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			message = ex.getMessage();
		}
		
		// HTTP response object.
		System.out.print("message");
		return ResponseEntity.status(200).body(message);		
	}
}