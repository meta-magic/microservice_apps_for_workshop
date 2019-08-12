package com.metamagic.order.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.metamagic.order.entities.Order;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, String> {

	public Flux<Order> findByUserId(String userId);
	
	public Mono<Order> findById(String id);
}
