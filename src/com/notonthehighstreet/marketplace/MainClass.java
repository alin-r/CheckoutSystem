package com.notonthehighstreet.marketplace;

// testing is also handled in CheckoutTest.java

import java.util.LinkedHashSet;
import java.util.Set;

import com.notonthehighstreet.marketplace.bean.ProductList;
import com.notonthehighstreet.marketplace.checkout.Checkout;
import com.notonthehighstreet.marketplace.exception.ProductListException;
import com.notonthehighstreet.marketplace.strategy.PromotionalRules;
import com.notonthehighstreet.marketplace.strategy.impl.QuantityDiscountRules;
import com.notonthehighstreet.marketplace.strategy.impl.TotalPriceDiscountRules;

public class MainClass {
	
	private static final int DISCOUNT_QUANTITY_1 = 2;
	private static final double DISCOUNT_PRICE_1 = 8.50;
	private static final double DISCOUNT_TOTAL_PRICE_1 = 60.0;
	private static final double DISCOUNT_TOTAL_PRICE_PERCENT_1 = 10.0;

	public static void main(String[] args) throws ProductListException {
		
		ProductList products = ProductList.getInstance();
		Set<PromotionalRules> promotionalRules = new LinkedHashSet<PromotionalRules>();
		
		products.addProduct("001", "Travel Card Holder", 9.25);
		products.addProduct("002", "Personalised cufflings", 45.00);
		products.addProduct("003", "Kids T-shirt", 19.95);
		
		promotionalRules.add(new QuantityDiscountRules(products.getProductByIndex(0), DISCOUNT_QUANTITY_1, DISCOUNT_PRICE_1));
		promotionalRules.add(new TotalPriceDiscountRules(DISCOUNT_TOTAL_PRICE_1, DISCOUNT_TOTAL_PRICE_PERCENT_1));
		
		Checkout co = new Checkout(promotionalRules);
		
		co.scan(products.getProductByIndex(0));
		co.scan(products.getProductByIndex(2));
		co.scan(products.getProductByIndex(0));
		
		System.out.println(co);
	}
	
}
