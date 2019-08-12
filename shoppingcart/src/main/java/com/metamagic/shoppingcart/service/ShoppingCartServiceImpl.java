package com.metamagic.shoppingcart.service;

import java.util.Date;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.shoppingcart.bean.ShoppingCartDTO;
import com.metamagic.shoppingcart.entities.AuditDetails;
import com.metamagic.shoppingcart.entities.ShoppingCart;
import com.metamagic.shoppingcart.repo.ShoppingCartRepository;

import ch.qos.logback.classic.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

	private static final String MIME_TYPE = "application/json";
	private static final String USER_AGENT = "Product WebClient";

	@Autowired
	private ShoppingCartRepository repo;

	@Override
	public Flux<ShoppingCart> findByUserId(String userId) {
		return repo.findByUserId(userId);
	}
	
	@Override
	public Mono<ShoppingCartDTO> fetchByUserId(String userId) {
		return 
			repo.findByUserId(userId)
			.collectList()
			.map(cart ->{
				ShoppingCartDTO dto = new ShoppingCartDTO(cart, new Double(0));
				dto.calculateTotal();
				return dto;
			});
	}

	@Override
	public void save(ShoppingCart shoppingCart) {
		shoppingCart.setId(UUID.randomUUID().toString());
		shoppingCart.setAuditDetails(new AuditDetails(1, "system", new Date(), "system", new Date()));
		repo.save(shoppingCart).subscribe();
	}

}
