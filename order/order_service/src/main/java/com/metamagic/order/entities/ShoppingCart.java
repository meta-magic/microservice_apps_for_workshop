package com.metamagic.order.entities;


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

	private Object productDetails;

	private Double price;

	private Integer qty;

}
