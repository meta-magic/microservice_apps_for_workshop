package com.metamagic.auth.service;

import com.metamagic.auth.bean.Authenticate;
import com.metamagic.auth.entities.UserAuthDetails;

import reactor.core.publisher.Mono;

public interface AuthService {
	
	public Authenticate authenticate(String userId, String password) ;
	
	public void save(UserAuthDetails userAuthDetails) ;

}
