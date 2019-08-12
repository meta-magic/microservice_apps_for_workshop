package com.metamagic.payment.bean;

import com.metamagic.payment.entities.ShoppingCart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

	private String paymentId;
	
	private java.util.List<ShoppingCart> shoppintCart;
}
