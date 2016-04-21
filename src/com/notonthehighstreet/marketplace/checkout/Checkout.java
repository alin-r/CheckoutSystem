package com.notonthehighstreet.marketplace.checkout;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.notonthehighstreet.marketplace.bean.Product;
import com.notonthehighstreet.marketplace.bean.ProductList;
import com.notonthehighstreet.marketplace.strategy.PromotionalRules;
import com.notonthehighstreet.marketplace.strategy.impl.QuantityDiscountRules;
import com.notonthehighstreet.marketplace.strategy.impl.TotalPriceDiscountRules;

/**
 * Class handling the adding of items in the basket and the basket update. </br>
 *
 */
public class Checkout {
	
	private Set<PromotionalRules> promotionalRules;
	/**	Items currently in the basket */
	private List<Product> basket;
	/** Current total price after discounts */
	private double totalPrice;
	
	private static final String EMPTY_BASKET_MESSAGE = "Shopping basket is empty.";
	private static final String BASKET_MESSAGE = "Basket: ";
	private static final String SEPARATOR = ","; 
	private static final String TOTAL_PRICE_MESSAGE = "Total price: "; 
	
	public Checkout(Set<PromotionalRules> promotionalRules) {
		this.promotionalRules = promotionalRules;
	}
	
	/**
	 * Adds a new item to the basket and updates the basket contents.
	 * @param item to be added
	 */
	public void scan(Product item) {
		this.getBasket().add(item);
		this.updateBasket();
	}
	
	/**
	 * @return the current total price of the items in the basket
	 */
	public double total() {
		return totalPrice;
	}
	
	public List<Product> getBasket() {
		if (basket == null) {
			basket = new ArrayList<>();
		} 
		return basket;
	}
	
	/**
	 * Updates the price of individual items in the basket and the total price, </br>
	 * based on corresponding applied strategy instance for promotional rules.
	 * 
	 * @see com.notonthehighstreet.marketplace.strategy.PromotionalRules
	 * @see com.notonthehighstreet.marketplace.strategy.impl.QuantityDiscountRules
	 * @see com.notonthehighstreet.marketplace.strategy.impl.TotalPriceDiscountRules
	 */
	private void updateBasket() {
		
		// if an item is removed from the basket at any point prior to update, and the removal causes 
		// similar items to not be eligible for the promotional price anymore, their prices need 
		// to be reset to default values
		getDefaultItemPrices(); 
		
		// apply promotional rules based on rule instance type
		for (PromotionalRules rule : promotionalRules) {
			if (rule instanceof QuantityDiscountRules) {
				rule.setProductDiscountPrice(basket);
			}
			
			if (rule instanceof TotalPriceDiscountRules) {
				totalPrice = rule.setTotalDiscountPrice(basket);
			}
		}
	}
	
	public String toString() {
		
		if (this.getBasket().isEmpty()) {
			return EMPTY_BASKET_MESSAGE;
		}
		
		StringBuilder output = new StringBuilder();
		output.append(BASKET_MESSAGE);
		for (Product item : this.getBasket()) {
			output.append(item.getCode()).append(SEPARATOR);
		}
		output.deleteCharAt(output.length() - 1);
		output.append(System.lineSeparator());
		output.append(TOTAL_PRICE_MESSAGE);
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("en", "GB"));
		
		output.append(nf.format(this.total()));
		output.append(System.lineSeparator());
		output.append(System.lineSeparator());
		
		return output.toString();
		
	}
	
	private void getDefaultItemPrices() {
		for (Product item : basket) {
			item.setPrice(ProductList.getInstance().getProductList()
					.get(ProductList.getInstance().getProductList().indexOf(item)).getPrice());
		}
	}
	
}
