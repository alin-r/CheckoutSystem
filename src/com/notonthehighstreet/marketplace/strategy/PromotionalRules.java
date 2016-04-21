package com.notonthehighstreet.marketplace.strategy;

import java.util.List;

import com.notonthehighstreet.marketplace.bean.Product;

public interface PromotionalRules {

	/**
	 * Applies a discount strategy to a single type of item in the basket, if the number of items </br> 
	 * of that type contained in the basket is at least the minimum quantity defined in the </br> 
	 * strategy implementor.
	 * 
	 * @param productList the list of items currently in the basket
	 */
	public void setProductDiscountPrice(List<Product> productList);
	
	/**
	 * Applies a discount strategy to the total amount form all items currently in the basket, </br>
	 * if that amount is greater than a minimum amount defined in the strategy implementor.
	 * @param productList the list of items currently in the basket
	 * @return the price after the discount is applied (if any) 
	 */
	public double setTotalDiscountPrice(List<Product> productList);
	
}
