package com.metamagic.payment.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.metamagic.payment.entities.Payment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ShoppingCartRepository extends ReactiveMongoRepository<Payment, String> {

	public Flux<Payment> findByUserId(String userId);
	
	public Mono<Payment> findById(String id);
}
