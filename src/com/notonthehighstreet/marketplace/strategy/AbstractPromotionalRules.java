package com.notonthehighstreet.marketplace.strategy;

import java.util.List;

import com.notonthehighstreet.marketplace.bean.Product;

public abstract class AbstractPromotionalRules implements PromotionalRules {
	
	/** 
	 * Implementation required for abstract method inherited from the interface - not used. 
	 * Is extended in a concrete class.
	 * @see com.notonthehighstreet.marketplace.strategy.impl.QuantityDiscountRules#setProductDiscountPrice(List)
	 */
	@Override
	public void setProductDiscountPrice(List<Product> productList) {
	}
	
	/**
	 * Implementation required for abstract method inherited from the interface - not used
	 * Is extended in a concrete class. 
	 * @see com.notonthehighstreet.marketplace.strategy.impl.TotalPriceDiscountRules#setTotalDiscountPrice(List)
	 */
	@Override
	public double setTotalDiscountPrice(List<Product> productList) {
		return 0;
	}
}
