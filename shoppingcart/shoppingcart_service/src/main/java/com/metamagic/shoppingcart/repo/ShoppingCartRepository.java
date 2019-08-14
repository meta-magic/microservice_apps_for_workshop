package com.metamagic.shoppingcart.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.metamagic.shoppingcart.entities.ShoppingCart;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ShoppingCartRepository extends ReactiveMongoRepository<ShoppingCart, String> {

	public Flux<ShoppingCart> findByUserIdAndActive(String userId, boolean active);
	
	public Mono<ShoppingCart> findById(String id);
}
