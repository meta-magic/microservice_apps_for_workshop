package com.metamagic.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.product.dto.ResponseBean;
import com.metamagic.product.entities.ProductDetails;
import com.metamagic.product.service.ProductService;

import reactor.core.publisher.Mono;

@CrossOrigin()
@RestController
@RequestMapping("/api/product")
public class ProductDetailsController {

	@Autowired
	private ProductService service;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> findall() {

		Mono<ResponseBean> response = service.findAll()
					.collectList()
					.map(productDetails -> new ResponseBean(true, "Products retrieved successfully", HttpStatus.OK + "", productDetails));

		return new ResponseEntity<Mono<ResponseBean>>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> findById(@PathVariable("id") String id) {
		
		Mono<ResponseBean> responseBean = service.findById(id)
					.map(productDetails -> new ResponseBean(true, "Product retrieved successfully", HttpStatus.OK + "", productDetails));

		return new ResponseEntity<Mono<ResponseBean>>(responseBean, HttpStatus.OK);
		
	}

	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> save(@RequestBody ProductDetails productDetails) {
		
		service.save(productDetails);
		ResponseBean response = new ResponseBean(true, "Record saved successfully", HttpStatus.OK + "", productDetails);
		return new ResponseEntity<Mono<ResponseBean>>(Mono.justOrEmpty(response), HttpStatus.OK);
		
	}
}
