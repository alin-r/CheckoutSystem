package com.notonthehighstreet.marketplace.bean;

import java.util.ArrayList;
import java.util.List;

import com.notonthehighstreet.marketplace.exception.ProductListException;

/**
 * Container class for products offered in the marketplace. </br>
 * Holds and returns a single instance. 
 *
 */
public class ProductList {
		
	private static ProductList instance = null;

	private List<Product> productList = new ArrayList<>();
	
	private static String CODE_OR_NAME_ALREADY_EXISTS = "An exception occured in ProductList: product code or name already exists";
	private static String PRODUCT_LIST_IS_NULL = "An exception occured in ProductList: product list is null";
	private static String PRODUCT_LIST_ARRAY_INDEX_OUT_OF_BOUNDS = "An exception occured in ProductList: index is out of bounds";

	private ProductList(){};
	
	/**
	 * Adds a new product to the {@link Product} list or throws a {@link ProductListException} </br>
	 * if the product already exists. 
	 * 
	 * @param code the product code (must be unique)
	 * @param name the product name (must be unique)
	 * @param price product initial price
	 * @throws ProductListException
	 */
	public void addProduct(String code, String name, double price) throws ProductListException {
		Product product = new Product(code, name, price);
		if (!productList.contains(product) && !nameExists(name)) {
			productList.add(product);
		} else {
			System.err.println(CODE_OR_NAME_ALREADY_EXISTS);
			throw new ProductListException();
		}
	}
	
	public static ProductList getInstance() {
		if (instance == null) {
			instance = new ProductList();
		}
		
		return instance;
	}
	
	public List<Product> getProductList() {
		if (productList == null) {
			productList = new ArrayList<>();
		}
		return getInstance().productList;
	}
	
	/**
	 * Returns a reference to the {@link Product} object corresponding to the </br> 
	 * index in the product list.
	 * @param index
	 * @return the product object reference
	 * @throws ProductListException
	 */
	public Product getProductByIndex(int index) throws ProductListException {
		if (productList == null) {
			System.err.println(PRODUCT_LIST_IS_NULL);
			throw new ProductListException();
		}
		
		if (productList.size() <= index) {
			System.err.println(PRODUCT_LIST_ARRAY_INDEX_OUT_OF_BOUNDS);
			throw new ProductListException();			
		}
		
		return getProductList().get(index);
		
	}
	
	/**
	 * Checks if the String argument matches any of the product names already in the list. </br>
	 * Used when adding a new product to the list.
	 * 
	 * @param name to be checked
	 * @return true if exists, false otherwise
	 */
	private boolean nameExists(String name) {
		for (Product product : productList) {
			if (product.getName().equals(name)) {
				return true;
			}
		}
		
		return false;
	}
	
}
