package com.metamagic.shoppingcart.entities;

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
@Document(value ="shopping_cart")
public class ShoppingCart extends BaseEntity {

	@Id()
	private String id;

	@Field(name = "userId")
	private String userId;

	@Field(name = "productId")
	private String productId;

	@Field(name = "price")
	private Double price;

	@Field(name = "qty")
	private Integer qty;

	private Object productDetails;

}
