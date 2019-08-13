package com.metamagic.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.auth.dto.Authenticate;
import com.metamagic.auth.dto.ResponseBean;
import com.metamagic.auth.entities.UserAuthDetails;
import com.metamagic.auth.service.AuthService;

import reactor.core.publisher.Mono;

@CrossOrigin()
@RestController
@RequestMapping("/api/user")
public class UserDetailsController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> authenticate(@RequestBody UserAuthDetails user){
		Authenticate authenticate = authService.authenticate(user.getUserId(), user.getPassword());
		ResponseBean response = new ResponseBean(true, "Authenticated service executed successfully", HttpStatus.OK+"", authenticate);
		return new ResponseEntity<Mono<ResponseBean>>( Mono.justOrEmpty(response), HttpStatus.OK);
	}
	
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> save(@RequestBody UserAuthDetails user){
		authService.save(user);
		ResponseBean response = new ResponseBean(true, "Record saved successfully", HttpStatus.OK+"", user);
		return new ResponseEntity<Mono<ResponseBean>>( Mono.justOrEmpty(response), HttpStatus.OK);
	}
}
