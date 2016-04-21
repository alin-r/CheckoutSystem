package com.notonthehighstreet.marketplace.strategy.impl;

import java.util.List;

import com.notonthehighstreet.marketplace.bean.Product;
import com.notonthehighstreet.marketplace.strategy.AbstractPromotionalRules;
import com.notonthehighstreet.marketplace.strategy.PromotionalRules;

/**
 * Implementation of {@link PromotionalRules} strategy. </br>
 * This implementation modifies the item price based on a minimum number of item </br>
 * instances in the {@link Checkout} basket.    
 *
 */
public class QuantityDiscountRules extends AbstractPromotionalRules {

	private Product discountedItem;
	private int minQuantity;
	private double discountedPrice;
	
	public QuantityDiscountRules(Product discountedItem, int minQuantity, double discountedPrice) {
		this.discountedItem = discountedItem;
		this.minQuantity = minQuantity;
		this.discountedPrice = discountedPrice;
	}
	
	/* (non-Javadoc)
	 * @see com.notonthehighstreet.marketplace.strategy.AbstractPromotionalRules#setProductDiscountPrice(java.util.List)
	 */
	@Override
	public void setProductDiscountPrice(List<Product> productList) {
		int quantity = 0;
		for (Product product : productList) {
			if (product.equals(discountedItem)) {
				quantity++;
			}
			if (quantity >= minQuantity ) {
				updateProductPrices(productList);
				break;
			}
		}
	}
	
	
	/**
	 * Updates the prices of an item type with the promotional price, if </br>
	 * such an item type has been found in the basket.
	 * This method is called from {@link QuantityDiscountRules#setProductDiscountPrice(List)}
	 * 
	 * @param productList
	 * 
	 */
	private void updateProductPrices(List<Product> productList) {
		for (Product product : productList) {
			if (product.equals(discountedItem)) {
				product.setPrice(discountedPrice);
			}
		}
	}
	
}
