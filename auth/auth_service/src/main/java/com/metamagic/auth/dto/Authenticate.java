package com.metamagic.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Authenticate {
	
	private boolean isValid;
	
	@JsonInclude(Include.NON_NULL)
	private String tokenId;
	
	

}
