package com.metamagic.shoppingcart.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.metamagic.shoppingcart.dto.ResponseBean;
import com.metamagic.shoppingcart.dto.ShoppingCartDTO;
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
	private static final String USER_AGENT = "shoppingcart";

	@Autowired
	private ShoppingCartRepository repo;

	@Override
	public Flux<ShoppingCart> findByUserId(String userId) {
		return repo.findByUserIdAndActive(userId, true);
	}
	
	@Override
	public void deleteByUserId(String userId) {
		List<ShoppingCart> shoppingCart = repo.findByUserIdAndActive(userId, true).collectList().block();
		for (ShoppingCart shoppingCart2 : shoppingCart) {
			shoppingCart2.setActive(false);
		}
		
		repo.saveAll(shoppingCart).subscribe();
		
	}
	@Override
	public Mono<ShoppingCartDTO> fetchByUserId(String userId) {
		return 
			repo.findByUserIdAndActive(userId, true)
			.collectList()
			.map(cart ->{
				ShoppingCartDTO dto = new ShoppingCartDTO(cart, new Double(0));
				dto.calculateTotal();
				
				dto.getShoppingCart().forEach((details) ->{

					try {
						Mono<ResponseBean> resp = this.fetchProductDetails(details.getProductId());
						ResponseBean response = resp.block();
						if (response != null && response.isSucess()) {
							details.setProductDetails(response.getData());
						}
					} catch (Exception e) {
						LOGGER.error("Unable to fetch product  for {} exception occurred {}", details.getId(), e.getMessage());
					}
					
				});
				return dto;
			});
	}

	@Override
	public void save(ShoppingCart shoppingCart) {
		shoppingCart.setId(UUID.randomUUID().toString());
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
