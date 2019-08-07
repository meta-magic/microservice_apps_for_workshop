package com.metamagic.product.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.product.bean.ResponseBean;
import com.metamagic.product.entities.ProductDetails;
import com.metamagic.product.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/product")
public class ProductDetailsController {
	
	
	@Autowired
	private ProductService authService;
	
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> findall(){		
		Flux<ProductDetails> obj =  authService.findAll();
		ResponseBean response = new ResponseBean(true, "Products retrieved successfully", HttpStatus.OK+"",obj.collectList().block());
		return new ResponseEntity<Mono<ResponseBean>>( Mono.justOrEmpty(response), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> findById(@PathVariable ("id") String id){		
		Mono<ProductDetails> obj =  authService.findById(id);
		ResponseBean response = new ResponseBean(true, "Products retrieved successfully", HttpStatus.OK+"",obj.block());
		return new ResponseEntity<Mono<ResponseBean>>( Mono.justOrEmpty(response), HttpStatus.OK);
	}
	
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> save(@RequestBody ProductDetails productDetails){
		productDetails.setId(UUID.randomUUID().toString());
		authService.save(productDetails);
		ResponseBean response = new ResponseBean(true, "Record saved successfully", HttpStatus.OK+"", productDetails);
		return new ResponseEntity<Mono<ResponseBean>>( Mono.justOrEmpty(response), HttpStatus.OK);
	}
}
