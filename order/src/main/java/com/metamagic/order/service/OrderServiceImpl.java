package com.metamagic.order.service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.order.entities.AuditDetails;
import com.metamagic.order.entities.Order;
import com.metamagic.order.repo.OrderRepository;

import ch.qos.logback.classic.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderRepository repo;

	@Override
	public Flux<Order> findByUserId(String userId) {
		return repo.findByUserId(userId);
	}
	 
	@Override
	public Mono<Order> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public void save(Order shoppingCart) {
		shoppingCart.calculateCartTotal();
		shoppingCart.setId(UUID.randomUUID().toString());
		shoppingCart.setOrderNo("ORD-"+Calendar.getInstance().getTimeInMillis());
		shoppingCart.setStatus("success");
		shoppingCart.setAuditDetails(new AuditDetails(1, "system", new Date(), "system", new Date()));
		repo.save(shoppingCart).subscribe();
	}

}
