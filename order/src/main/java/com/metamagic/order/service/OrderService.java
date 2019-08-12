package com.metamagic.order.service;

import com.metamagic.order.entities.Order;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
	
	public void save(Order order) ;
	
	public Flux<Order> findByUserId(String userId) ;
	
	public Mono<Order> findById(String id) ;
		
}
