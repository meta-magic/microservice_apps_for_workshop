package com.metamagic.auth.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document(value ="user_details")
public class UserAuthDetails extends BaseEntity {

	@Id()
	private String id;

	@Field(name = "userId")
	private String userId;

	@Field(name = "password")
	private String password;

	@Field(name = "firstName")
	private String firstName;

	@Field(name = "lastName")
	private String lastName;

	@Field(name = "emailId")
	private String emailId;

	@Field(name = "phoneNo")
	private String phoneNo;

}
