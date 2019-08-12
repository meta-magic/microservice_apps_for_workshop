package com.metamagic.payment.service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.payment.entities.AuditDetails;
import com.metamagic.payment.entities.Payment;
import com.metamagic.payment.repo.ShoppingCartRepository;

import ch.qos.logback.classic.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentServiceImpl implements PaymentService {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private ShoppingCartRepository repo;

	@Override
	public Flux<Payment> findByUserId(String userId) {
		return repo.findByUserId(userId);
	}
	 
	@Override
	public Mono<Payment> findById(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public void save(Payment shoppingCart) {
		shoppingCart.calculateCartTotal();
		shoppingCart.setId(UUID.randomUUID().toString());
		shoppingCart.setPaymentId("ONL-"+Calendar.getInstance().getTimeInMillis());
		shoppingCart.setStatus("success");
		shoppingCart.setAuditDetails(new AuditDetails(1, "system", new Date(), "system", new Date()));
		repo.save(shoppingCart).subscribe();
	}

}
