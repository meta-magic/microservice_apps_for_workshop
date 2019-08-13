package com.metamagic.product.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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

	@JsonInclude(Include.NON_NULL)
	@Field(name = "productNo")
	private String productNo;

	@Field(name = "name")
	private String name;

	@Field(name = "primaryicon")
	private String primaryicon;
	
	@JsonInclude(Include.NON_NULL)
	@Field(name = "description")
	private String description;

	@Field(name = "price")
	private Double price;

	@JsonInclude(Include.NON_NULL)
	@Field(name = "category")
	private String category;
	
	@JsonInclude(Include.NON_NULL)
	private Object reviews;

}
