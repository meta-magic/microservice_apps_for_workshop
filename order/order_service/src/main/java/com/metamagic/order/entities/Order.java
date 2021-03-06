package com.metamagic.order.entities;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document(value ="order")
public class Order extends BaseEntity {

	@JsonIgnore
	@Id()
	private String id;

	@Field(name = "orderNo")
	private String orderNo;

	@JsonIgnore
	@Field(name = "userId")
	private String userId;
	
	@Field(name = "paymentId")
	private String paymentId;

	@Field(name = "status")
	private String status;
	
	@Field(name = "shoppintCart")
	private java.util.List<ShoppingCart> shoppintCart;

	private Double sum;
	
	@Field(name = "orderDate")
	private Date orderDate = new Date();

	
	public void calculateCartTotal(){
		this.sum = new Double(0);
		if(this.shoppintCart !=null){
			this.shoppintCart.forEach((cart) -> {
				this.sum = this.sum + cart.getPrice();
			});
		}
	}
}
