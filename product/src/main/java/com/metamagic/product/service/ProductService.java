package com.metamagic.product.service;

import com.metamagic.product.entities.ProductDetails;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
	
	public void save(ProductDetails userAuthDetails) ;
	
	public Flux<ProductDetails> findAll() ;
	
	public Mono<ProductDetails> findById(String id) ;

}
