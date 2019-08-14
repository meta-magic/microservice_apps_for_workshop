package com.metamagic.order.service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.metamagic.order.dto.ResponseBean;
import com.metamagic.order.entities.AuditDetails;
import com.metamagic.order.entities.Order;
import com.metamagic.order.repo.OrderRepository;

import ch.qos.logback.classic.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(OrderServiceImpl.class);

	private static final String MIME_TYPE = "application/json";
	private static final String USER_AGENT = "order";

	@Autowired
	private OrderRepository repo;

	@Override
	public Flux<Order> findByUserId(String userId) {
		return repo.findByUserId(userId)
			.flatMap(this::retrievProduct);
	}
	 
	private Mono<Order> retrievProduct(Order order){
		order.getShoppintCart()
			.forEach((cart)->{
				try {
					Mono<ResponseBean> resp = this.fetchProductDetails(cart.getProductId());
					ResponseBean response = resp.block();
					if (response != null && response.isSucess()) {
						cart.setProductDetails(response.getData());
					}
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error("Unable to fetch product  for {} exception occurred {}", cart.getProductId(), e.getMessage());
				}
			});
		
		return Mono.just(order);
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
	
	private Mono<ResponseBean> fetchProductDetails(String productId) {
		String PRODUCT_APP_BASE_URL = System.getenv("PRODUCT_APP_BASE_URL"); // "http://localhost:8080";
		if (PRODUCT_APP_BASE_URL == null) {
			LOGGER.error("Unable to get PRODUCT_APP_BASE_URL from system env");
			return Mono.just(new ResponseBean());
		}
		String PRODUCT_APP_URL = PRODUCT_APP_BASE_URL + "/api/product/";

		return WebClient.builder().baseUrl(PRODUCT_APP_URL).defaultHeader(HttpHeaders.CONTENT_TYPE, MIME_TYPE)
				.defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT).build().get().uri("" + productId).retrieve()
				.bodyToMono(ResponseBean.class);

	}

}
