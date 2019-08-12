package com.metamagic.payment.service;

import com.metamagic.payment.entities.Payment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentService {
	
	public void save(Payment userAuthDetails) ;
	
	public Flux<Payment> findByUserId(String userId) ;
	
	public Mono<Payment> findById(String id) ;
		
}
