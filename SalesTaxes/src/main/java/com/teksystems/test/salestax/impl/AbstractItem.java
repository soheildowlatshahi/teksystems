package com.teksystems.test.salestax.impl;

import com.teksystems.test.salestax.Item;
import com.teksystems.test.salestax.ItemCategory;

/**
 * 
 * @see com.teksystems.test.salestax.Item interface.
 * @author Soheil Dowlatshahi(soheildowlatshahi@yahoo.com)
 *
 */
public abstract class AbstractItem implements Item {
	
	/**
	 * 
	 */
	private final String name;
	
	/**
	 * This is total price and it's not price of unit or fee
	 */
	private final double price;
	
	
	/**
	 * 
	 */
	private final ItemCategory category;
	
	/**
	 * 
	 */
	private final int count;
	
	
	/**
	 * 
	 * @param name
	 * @param price This is total price and it's not price of unit or fee
	 * @param count
	 */
	protected AbstractItem(final String name, final double price, final ItemCategory category, final int count) {
		super();
		
		if (category == null) {
			throw new IllegalArgumentException("No category defined for the given purchased item!");
		}
		
		this.name = name;
		this.price = price;
		this.category = category;
		this.count = count;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return  total price and it's not price of unit or fee
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * 
	 * @return
	 */
	public ItemCategory getCategory() {
		return category;
	}
	/**
	 * 
	 * @return
	 */
	public int getCount() {
		return count;
	}

}
