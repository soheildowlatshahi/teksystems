package com.teksystems.test.salestax;

import com.teksystems.test.salestax.impl.AbstractItem;

/**
 * 
 * @author Soheil Dowlatshahi
 *
 */
public final class PurchasedItem extends AbstractItem {
	
	
	/**
	 * 
	 * @param name
	 * @param price
	 * @param category
	 */
	private PurchasedItem(final String name, final double price, final ItemCategory category, final int count) {
		super(name, price, category, count);
	}
	
	
	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		final StringBuilder sb = new StringBuilder("PurchasedItem(");
		
		sb.append("name: ").append(getName());
		sb.append(", ");
		sb.append("price: ").append(getPrice());
		sb.append(", ");
		sb.append("category: ").append(getCategory());
		sb.append(", ");
		sb.append("count: ").append(getCount());	
		sb.append(")");
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param name
	 * @param price
	 * @param category
	 * @return
	 */
	public static PurchasedItem newPurchasedItem(
			final String name, 
			final double price, 
			final ItemCategory category
			) {
		return new PurchasedItem(name, price, category, 1);
	}
	
	/**
	 * 
	 * @param name
	 * @param price
	 * @param category
	 * @param count
	 * @return
	 */
	public static PurchasedItem newPurchasedItem(final String name, 
			final double price, 
			final ItemCategory category,
			final int count) {
		return new PurchasedItem(name, price, category, count);
	}

}
