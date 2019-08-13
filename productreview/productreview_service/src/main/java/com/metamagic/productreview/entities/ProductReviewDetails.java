package com.metamagic.productreview.entities;

import java.util.Date;

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
@Document(value ="product_review_details")
public class ProductReviewDetails extends BaseEntity {

	@Id()
	private String id;

	@Field(name = "productId")
	private String productId;

	@Field(name = "user")
	private String user;

	@JsonInclude(Include.NON_NULL)
	@Field(name = "reviewdescription")
	private String reviewdescription;

	@JsonInclude(Include.NON_NULL)
	@Field(name = "rating")
	private Integer rating;

	@Field(name = "reviewdate")
	private Date reviewdate = new Date();

}
