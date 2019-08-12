package com.metamagic.shoppingcart.dto;


import java.util.ArrayList;
import java.util.List;

import com.metamagic.shoppingcart.entities.ShoppingCart;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShoppingCartDTO {

	private List<ShoppingCart> shoppingCart = new ArrayList<ShoppingCart>();
	Double sum = new Double(0);
	
	public void addShoppingCart(ShoppingCart cart){
		this.shoppingCart.add(cart);
		this.sum = this.sum + cart.getPrice();
	}
	
	public void calculateTotal(){
		this.sum = new Double(0);
		for (ShoppingCart shoppingCart2 : shoppingCart) {
			this.sum = this.sum + shoppingCart2.getPrice();
		}
	}
	
}
