package com.metamagic.payment.service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.metamagic.payment.dto.OrderDTO;
import com.metamagic.payment.dto.ResponseBean;
import com.metamagic.payment.entities.AuditDetails;
import com.metamagic.payment.entities.Payment;
import com.metamagic.payment.repo.ShoppingCartRepository;

import ch.qos.logback.classic.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentServiceImpl implements PaymentService {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(PaymentServiceImpl.class);

	private static final String MIME_TYPE = "application/json";
	private static final String USER_AGENT = "Payment WebClient";

	@Autowired
	private ShoppingCartRepository repo;

	@Override
	public Flux<Payment> findByUserId(String userId) {
		return repo.findByUserId(userId);
	}
	 
	@Override
	public Mono<Payment> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public void save(Payment payment) {
		payment.calculateCartTotal();
		payment.setId(UUID.randomUUID().toString());
		payment.setPaymentId("ONL-"+Calendar.getInstance().getTimeInMillis());
		payment.setStatus("success");
		payment.setAuditDetails(new AuditDetails(1, "system", new Date(), "system", new Date()));
		repo.save(payment).subscribe();
		
		this.createOrder(payment).subscribe();
		this.emptyShoppingCart(payment.getUserId()).subscribe();
	}
	
	private Mono<ResponseBean>  createOrder(Payment payment){
		
		OrderDTO orderDto = new OrderDTO(payment.getId(), payment.getShoppintCart());
		

		String ORDER_APP_BASE_URL = System.getenv("ORDER_APP_BASE_URL"); // "http://localhost:8080";
		if (ORDER_APP_BASE_URL == null) {
			LOGGER.error("Unable to get ORDER_APP_BASE_URL from system env");
			return Mono.just(new ResponseBean());
		}
		String ORDER_SAVE_URL = ORDER_APP_BASE_URL + "/api/order/";
		
		return WebClient.builder().baseUrl(ORDER_SAVE_URL)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MIME_TYPE)
				.defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
				.defaultHeader("tokenid", payment.getUserId())
				.build().post().body(orderDto).retrieve()
				.bodyToMono(ResponseBean.class);

	
	}
	
	private Mono<ResponseBean>  emptyShoppingCart(String id){
		
		String SHOPPINGCART_APP_BASE_URL = System.getenv("SHOPPINGCART_APP_BASE_URL"); // "http://localhost:8080";
		if (SHOPPINGCART_APP_BASE_URL == null) {
			LOGGER.error("Unable to get SHOPPINGCART_APP_BASE_URL from system env");
			return Mono.just(new ResponseBean());
		}
		String SHOPPINGCART_DELETE_URL = SHOPPINGCART_APP_BASE_URL + "/api/shoppingcart/user/"+id;
		
		return WebClient.builder().baseUrl(SHOPPINGCART_DELETE_URL)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MIME_TYPE)
				.defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
				.defaultHeader("tokenid", id)
				.build().delete().retrieve()
				.bodyToMono(ResponseBean.class);

	
	}
	
	

}
