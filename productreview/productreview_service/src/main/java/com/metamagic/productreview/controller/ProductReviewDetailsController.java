package com.metamagic.productreview.controller;

import java.util.UUID;

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

import com.metamagic.productreview.dto.ResponseBean;
import com.metamagic.productreview.entities.ProductReviewDetails;
import com.metamagic.productreview.service.ProductReviewService;

import reactor.core.publisher.Mono;

@CrossOrigin()
@RestController
@RequestMapping("/api/review")
public class ProductReviewDetailsController {
	
	@Autowired
	private ProductReviewService service;
	
	@GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> findall(@PathVariable ("id") String id){		
		
		Mono<ResponseBean> response = service.findByProductId(id)
								.collectList()
								.map(productreviews -> new ResponseBean(true, "Productreview retrieved successfully", HttpStatus.OK+"",productreviews));

		return new ResponseEntity<Mono<ResponseBean>>( response, HttpStatus.OK);
		
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> findById(@PathVariable ("id") String id){		
		
		Mono<ResponseBean> response =  service.findById(id)
									.map(productreviews -> new ResponseBean(true, "Productreview retrieved successfully", HttpStatus.OK+"",productreviews));
		
		return new ResponseEntity<Mono<ResponseBean>>( response, HttpStatus.OK);
	}
	
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> save(@RequestBody ProductReviewDetails productReviewDetails){
		
		productReviewDetails.setId(UUID.randomUUID().toString());
		service.save(productReviewDetails);
		
		ResponseBean response = new ResponseBean(true, "Record saved successfully", HttpStatus.OK+"", productReviewDetails);
		return new ResponseEntity<Mono<ResponseBean>>( Mono.justOrEmpty(response), HttpStatus.OK);
		
	}
}
