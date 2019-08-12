package com.metamagic.productreview.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.productreview.entities.AuditDetails;
import com.metamagic.productreview.entities.ProductReviewDetails;
import com.metamagic.productreview.repo.ProductDetailsRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

	@Autowired
	private ProductDetailsRepository repo;
	
	@Override
	public Flux<ProductReviewDetails> findByProductId(String productId){
		return repo.findByProductId(productId);
	}
	
	@Override
	public void save(ProductReviewDetails entity) {
		entity.setId(UUID.randomUUID().toString());
		entity.setAuditDetails(new AuditDetails(1, "system", new Date(), "system", new Date()));
		repo.save(entity).subscribe();
	}
	
	@Override
	public Mono<ProductReviewDetails> findById(String id) {
		return repo.findById(id);
	}
}
