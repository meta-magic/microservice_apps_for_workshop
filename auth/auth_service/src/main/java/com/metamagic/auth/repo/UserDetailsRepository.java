package com.metamagic.auth.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.metamagic.auth.entities.UserAuthDetails;

import reactor.core.publisher.Mono;

@Repository
public interface UserDetailsRepository extends ReactiveMongoRepository<UserAuthDetails, String> {

	public Mono<UserAuthDetails> findByUserIdAndActive(String userId, boolean active);
}
