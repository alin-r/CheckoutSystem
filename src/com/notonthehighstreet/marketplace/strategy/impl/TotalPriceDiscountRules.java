package com.notonthehighstreet.marketplace.strategy.impl;

import java.util.List;

import com.notonthehighstreet.marketplace.bean.Product;
import com.notonthehighstreet.marketplace.strategy.AbstractPromotionalRules;
import com.notonthehighstreet.marketplace.strategy.PromotionalRules;

/**
 * Implementation of {@link PromotionalRules} strategy. </br>
 * This implementation discounts the total price from all items in the basket, </br> 
 * if the price exceeds a minimum threshold defined in the constructor of this class.</br>
 *
 */
public class TotalPriceDiscountRules extends AbstractPromotionalRules {
	
	private double minTotalPrice;
	private double discountPercent;

	public TotalPriceDiscountRules(double minTotalPrice, double discountPercent) {
		this.minTotalPrice = minTotalPrice;
		this.discountPercent = discountPercent;
	}
	
	/* (non-Javadoc)
	 * @see com.notonthehighstreet.marketplace.strategy.AbstractPromotionalRules#setTotalDiscountPrice(java.util.List)
	 */
	@Override
	public double setTotalDiscountPrice(List<Product> productList) {
		
		double totalPrice = 0D;
		for (Product product : productList) {
			totalPrice += product.getPrice();
		}
		
		if (totalPrice > minTotalPrice) {
			totalPrice -= totalPrice * discountPercent / 100;
		}
		
		return totalPrice;
	}

}
