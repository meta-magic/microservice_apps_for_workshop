package com.metamagic.product.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.metamagic.product.entities.ProductDetails;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductDetailsRepository extends ReactiveMongoRepository<ProductDetails, String> {

	public Flux<ProductDetails> findAll();
	
	public Mono<ProductDetails> findById(String id);
}
