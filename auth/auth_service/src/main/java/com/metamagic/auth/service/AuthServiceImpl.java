package com.metamagic.auth.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metamagic.auth.dto.Authenticate;
import com.metamagic.auth.entities.AuditDetails;
import com.metamagic.auth.entities.UserAuthDetails;
import com.metamagic.auth.repo.UserDetailsRepository;

import reactor.core.publisher.Mono;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserDetailsRepository repo;

	@Override
	public Authenticate authenticate(String userId, String password) {
		Authenticate authenticate = new Authenticate();
		Mono<UserAuthDetails> userAuthDetails = repo.findByUserIdAndActive(userId, true);
		if (userAuthDetails != null) {
			UserAuthDetails _userAuthDetails = userAuthDetails.block() ;
			if(_userAuthDetails !=null){
				authenticate.setValid(_userAuthDetails.getPassword().equals(password));
				if(authenticate.isValid())
					authenticate.setTokenId(_userAuthDetails.getId()); // CREATE JWT TOKEN				
			}
		} else {
			authenticate.setValid(false);
		}
		
		return authenticate;
	}
	
	@Override
	public void save(UserAuthDetails userAuthDetails) {
		userAuthDetails.setId(UUID.randomUUID().toString());
		userAuthDetails.setAuditDetails(new AuditDetails(1, "system", new Date(), "system", new Date()));
		repo.save(userAuthDetails).subscribe();
	}
}
