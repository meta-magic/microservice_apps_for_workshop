package com.metamagic.shoppingcart.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.metamagic.shoppingcart.dto.ResponseBean;
import com.metamagic.shoppingcart.entities.ShoppingCart;
import com.metamagic.shoppingcart.service.ShoppingCartService;

import reactor.core.publisher.Mono;

@CrossOrigin()
@RestController
@RequestMapping("/api/shoppingcart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService service;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> findall() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String tokenId = request.getHeader("tokenid");
		if(tokenId == null){
			tokenId = "";
		}
		
		Mono<ResponseBean> response = service.fetchByUserId(tokenId)
					.map(details -> new ResponseBean(true, "Record retrieved successfully", HttpStatus.OK + "", details));

		return new ResponseEntity<Mono<ResponseBean>>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> delete(@PathVariable ("id") String userId) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		
		service.deleteByUserId(userId);
		Mono<ResponseBean> response =  Mono.just(new ResponseBean(true, "Record deleted successfully", HttpStatus.OK + "", userId));

		return new ResponseEntity<Mono<ResponseBean>>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> save(@RequestBody ShoppingCart shoppingcart) {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String tokenId = request.getHeader("tokenid");
		if(tokenId != null){
			shoppingcart.setUserId(tokenId);
		}
		
		service.save(shoppingcart);
		ResponseBean response = new ResponseBean(true, "Record saved successfully", HttpStatus.OK + "", shoppingcart);
		return new ResponseEntity<Mono<ResponseBean>>(Mono.justOrEmpty(response), HttpStatus.OK);
		
	}
}
