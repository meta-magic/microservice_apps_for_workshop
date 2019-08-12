package com.metamagic.productreview.service;

import com.metamagic.productreview.entities.ProductReviewDetails;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductReviewService {
	
	public void save(ProductReviewDetails entity) ;
	
	public Flux<ProductReviewDetails> findByProductId(String productId) ;
	
	public Mono<ProductReviewDetails> findById(String id) ;

}
