package com.metamagic.product.service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.metamagic.product.dto.ResponseBean;
import com.metamagic.product.entities.AuditDetails;
import com.metamagic.product.entities.ProductDetails;
import com.metamagic.product.repo.ProductDetailsRepository;

import ch.qos.logback.classic.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ProductServiceImpl.class);

	private static final String MIME_TYPE = "application/json";
	private static final String USER_AGENT = "Product WebClient";

	@Autowired
	private ProductDetailsRepository repo;

	@Override
	public Flux<ProductDetails> findAll() {
		return repo.findAll();
	}

	@Override
	public void save(ProductDetails productDetails) {
		productDetails.setId(UUID.randomUUID().toString());
		productDetails.setPrimaryicon("PD_Primary_Image"+Calendar.getInstance().getTimeInMillis()+".jpeg");
		productDetails.setAuditDetails(new AuditDetails(1, "system", new Date(), "system", new Date()));
		repo.save(productDetails).subscribe();
	}

	@Override
	public Mono<ProductDetails> findById(String id) {

		return repo.findById(id).flatMap(productDetails -> {
			try {
				Mono<ResponseBean> resp = this.fetchProductReveiws(productDetails.getId());
				ResponseBean response = resp.block();
				if (response != null && response.isSucess()) {
					productDetails.setReviews(response.getData());
				}
			} catch (Exception e) {
				LOGGER.error("Unable to fetch product reviews for {} exception occurred {}", id, e.getMessage());
			}

			return Mono.just(productDetails);
		});
	}

	private Mono<ResponseBean> fetchProductReveiws(String productId) {
		String REVIEW_APP_BASE_URL = System.getenv("REVIEW_APP_BASE_URL"); // "http://localhost:8080";
		if (REVIEW_APP_BASE_URL == null) {
			LOGGER.error("Unable to get REVIEW_APP_BASE_URL from system env");
			return Mono.just(new ResponseBean());
		}
		String PRODUCT_REVIEW_URL = REVIEW_APP_BASE_URL + "/api/review/";
		return WebClient.builder().baseUrl(PRODUCT_REVIEW_URL).defaultHeader(HttpHeaders.CONTENT_TYPE, MIME_TYPE)
				.defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT).build().get().uri("/product/" + productId).retrieve()
				.bodyToMono(ResponseBean.class);

	}
}
