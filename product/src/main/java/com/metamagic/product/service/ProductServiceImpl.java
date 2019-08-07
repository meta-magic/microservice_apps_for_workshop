package com.metamagic.product.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.product.entities.AuditDetails;
import com.metamagic.product.entities.ProductDetails;
import com.metamagic.product.repo.ProductDetailsRepository;

import reactor.core.publisher.Flux;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDetailsRepository repo;
	
	@Override
	public Flux<ProductDetails> findAll() {
		return repo.findAll();
	}
	
	@Override
	public void save(ProductDetails productDetails) {
		productDetails.setId(UUID.randomUUID().toString());
		productDetails.setAuditDetails(new AuditDetails(1, "system", new Date(), "system", new Date()));
		repo.save(productDetails).subscribe();
	}
}
