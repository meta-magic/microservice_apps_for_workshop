package com.metamagic.payment.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {

	private String productId;

	private Double price;

	private Integer qty;

}
