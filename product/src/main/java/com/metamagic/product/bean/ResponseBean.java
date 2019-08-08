package com.metamagic.product.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBean {

	private boolean sucess;
	
	private String message;
	
	private String status;
	
	private Object data;
 
}
