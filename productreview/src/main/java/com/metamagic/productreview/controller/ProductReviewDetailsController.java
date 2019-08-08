package com.metamagic.productreview.controller;

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

import com.metamagic.productreview.bean.ResponseBean;
import com.metamagic.productreview.entities.ProductReviewDetails;
import com.metamagic.productreview.service.ProductReviewService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/review")
public class ProductReviewDetailsController {
	
	
	@Autowired
	private ProductReviewService service;
	
	@GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> findall(@PathVariable ("id") String id){		
		Flux<ProductReviewDetails> obj =  service.findByProductId(id);
		ResponseBean response = new ResponseBean(true, "Productreview retrieved successfully", HttpStatus.OK+"",obj.collectList().block());
		return new ResponseEntity<Mono<ResponseBean>>( Mono.justOrEmpty(response), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> findById(@PathVariable ("id") String id){		
		Mono<ProductReviewDetails> obj =  service.findById(id);
		ResponseBean response = new ResponseBean(true, "Productreview retrieved successfully", HttpStatus.OK+"",obj.block());
		return new ResponseEntity<Mono<ResponseBean>>( Mono.justOrEmpty(response), HttpStatus.OK);
	}
	
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> save(@RequestBody ProductReviewDetails productDetails){
		productDetails.setId(UUID.randomUUID().toString());
		service.save(productDetails);
		ResponseBean response = new ResponseBean(true, "Record saved successfully", HttpStatus.OK+"", productDetails);
		return new ResponseEntity<Mono<ResponseBean>>( Mono.justOrEmpty(response), HttpStatus.OK);
	}
}
