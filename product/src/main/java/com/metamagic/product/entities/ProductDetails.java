package com.metamagic.product.entities;

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
@Document(value ="product_details")
public class ProductDetails extends BaseEntity {

	@Id()
	private String id;

	@Field(name = "productId")
	private String productId;

	@Field(name = "name")
	private String name;

	@Field(name = "description")
	private String description;

	@Field(name = "price")
	private Double price;

	@Field(name = "category")
	private String category;

}