package com.metamagic.product.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.metamagic.product.bean.ResponseBean;
import com.metamagic.product.entities.AuditDetails;
import com.metamagic.product.entities.ProductDetails;
import com.metamagic.product.repo.ProductDetailsRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

	private final WebClient reviewWebClient;

	private static final String MIME_TYPE = "application/json";
	private static final String REVIEW_APP_BASE_URL = "http://localhost:8080";
	private static final String USER_AGENT = "Spring 5 WebClient";

	private static final String PRODUCT_REVIEW_URL = REVIEW_APP_BASE_URL + "/api/review/";

	public ProductServiceImpl() {

		this.reviewWebClient = WebClient.builder().baseUrl(PRODUCT_REVIEW_URL)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MIME_TYPE).defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
				.build();
	}

	@Autowired
	private ProductDetailsRepository repo;

	@Override
	public Flux<ProductDetails> findAll() {
		return repo.findAll();
	}

	@Override
	public void save(ProductDetails productDetails) {
		productDetails.setId(UUID.randomUUID().toString());
		productDetails.setAuditDetails(new AuditDetails(1, "system", new Date(), "system", new Date()));
		repo.save(productDetails).subscribe();
	}

	@Override
	public Mono<ProductDetails> findById(String id) {

		return repo.findById(id).flatMap(productDetails -> {
			Mono<ResponseBean> resp = this.fetchProductReveiws(productDetails.getId());
			productDetails.setReviews(resp.block());
			return Mono.just(productDetails);
		});
	}

	private Mono<ResponseBean> fetchProductReveiws(String productId) {
		try {
			return this.reviewWebClient.get().uri("/product/" + productId).retrieve().bodyToMono(ResponseBean.class);

		} catch (Exception e) {

		}
		return Mono.justOrEmpty(new ResponseBean());
	}
}
