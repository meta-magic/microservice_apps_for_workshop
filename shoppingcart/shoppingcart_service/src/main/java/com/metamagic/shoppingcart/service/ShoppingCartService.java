package com.metamagic.shoppingcart.service;

import com.metamagic.shoppingcart.dto.ShoppingCartDTO;
import com.metamagic.shoppingcart.entities.ShoppingCart;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ShoppingCartService {
	
	public void save(ShoppingCart userAuthDetails) ;
	
	public Flux<ShoppingCart> findByUserId(String userId) ;
	
	public void deleteByUserId(String userId) ;
	
	public Mono<ShoppingCartDTO> fetchByUserId(String userId) ;
	
}
