package com.metamagic.product.service;

import com.metamagic.product.entities.ProductDetails;

import reactor.core.publisher.Flux;

public interface ProductService {
	
	public void save(ProductDetails userAuthDetails) ;
	
	public Flux<ProductDetails> findAll() ;

}
