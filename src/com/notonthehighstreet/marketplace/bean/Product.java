package com.notonthehighstreet.marketplace.bean;

import java.io.Serializable;

/**
 * Model class for product definition. </br></br>
 * Products should be added only using the {@link ProductList} static instance </br>
 * instead of individual creation, to avoid duplicates or inconsistent data.
 *
 */
public class Product implements Serializable {
	
	private static final long serialVersionUID = -237096234191601673L;
	
	private String code;
	private String name;
	private double price;
	
	private Product(){};
	
	Product(String code, String name, double price) {
		this();
		this.code = code;
		this.name = name;
		this.price = price;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	
	/** 
	 * Two Product objects are considered equal if their code is equal to each other. </br>
	 * However, this situation should never happen as each product should have an </br>
	 * unique code. 
	 * Additionally, each product should also have its own unique name. 
	 * @see com.notonthehighstreet.marketplace.bean.ProductList#addProduct(String, String, double)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
}
