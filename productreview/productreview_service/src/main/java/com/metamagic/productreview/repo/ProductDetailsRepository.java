package com.metamagic.productreview.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.metamagic.productreview.entities.ProductReviewDetails;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductDetailsRepository extends ReactiveMongoRepository<ProductReviewDetails, String> {

	public Flux<ProductReviewDetails> findAll();
	
	public Mono<ProductReviewDetails> findById(String id);
	
	public Flux<ProductReviewDetails> findByProductId(String productId) ;
}
