package com.notonthehighstreet.marketplace.test;

import java.text.NumberFormat;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

import com.notonthehighstreet.marketplace.bean.ProductList;
import com.notonthehighstreet.marketplace.checkout.Checkout;
import com.notonthehighstreet.marketplace.exception.ProductListException;
import com.notonthehighstreet.marketplace.strategy.PromotionalRules;
import com.notonthehighstreet.marketplace.strategy.impl.QuantityDiscountRules;
import com.notonthehighstreet.marketplace.strategy.impl.TotalPriceDiscountRules;

public class CheckoutTest {

	ProductList products = ProductList.getInstance();
	Set<PromotionalRules> promotionalRules = new LinkedHashSet<PromotionalRules>();

	@Test
	public void doTests() throws ProductListException {
		buildInits();
		testCheckout1();
		testCheckout2();
		testCheckout3();
	}
	
	public void testCheckout1() throws ProductListException {
		
		Checkout co = new Checkout(promotionalRules);
		
		co.scan(products.getProductByIndex(0));
		co.scan(products.getProductByIndex(1));
		co.scan(products.getProductByIndex(2));
		
		double price = co.total();
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		
		assert(nf.format(price).equals(nf.format(66.78)));
		
		System.out.println(co);
	}
	
	public void testCheckout2() throws ProductListException {
		
		Checkout co = new Checkout(promotionalRules);
		
		co.scan(products.getProductByIndex(0));
		co.scan(products.getProductByIndex(2));
		co.scan(products.getProductByIndex(0));
		
		double price = co.total();
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		
		assert(nf.format(price).equals(nf.format(36.95)));
		
		System.out.println(co);
	}
	
	public void testCheckout3() throws ProductListException {
		
		Checkout co = new Checkout(promotionalRules);
		
		co.scan(products.getProductByIndex(0));
		co.scan(products.getProductByIndex(1));
		co.scan(products.getProductByIndex(0));
		co.scan(products.getProductByIndex(2));
		
		Double price = co.total();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		
		assert(nf.format(price).equals(nf.format(73.76)));
		
		System.out.println(co);
	}		
	
	private void buildInits() throws ProductListException {
		
		products.addProduct("001", "Travel Card Holder", 9.25);
		products.addProduct("002", "Personalised cufflings", 45.00);
		products.addProduct("003", "Kids T-shirt", 19.95);
		
		promotionalRules.add(new QuantityDiscountRules(products.getProductByIndex(0), 2, 8.5));
		promotionalRules.add(new TotalPriceDiscountRules(60, 10));
	}
}
