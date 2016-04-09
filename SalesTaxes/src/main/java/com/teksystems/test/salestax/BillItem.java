package com.teksystems.test.salestax;

import java.math.BigDecimal;

import com.teksystems.test.salestax.impl.AbstractItem;

/**
 * 
 * @author Soheil Dowlatshahi(soheildowlatshahi@yahoo.com)
 *
 */
public class BillItem extends AbstractItem {
	
	/**
	 * 
	 */
	private final double salesTax;
	
	/**
	 * 
	 * @param name
	 * @param price this total price
	 * @param count
	 */
	private BillItem(final String name, final double price, final ItemCategory category, final double salesTax, final int count) {
		super(name, price, category, count);
		this.salesTax = salesTax;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public double getSalesTax() {
		return salesTax;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		final StringBuilder sb = new StringBuilder("BillItem(");
		
		sb.append("name: ").append(getName());
		sb.append(", ");
		sb.append("price: ").append(getPrice());
		sb.append(", ");
		sb.append("category: ").append(getCategory());
		sb.append(", ");
		sb.append("salesTax: ").append(this.salesTax);
		sb.append(", ");
		sb.append("count: ").append(getCount());
		sb.append(")");
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param item
	 * @param priceAfterTax
	 * @return
	 */
	public static BillItem newInstance(final Item item, final double salesTax) {
		
		if (item == null) {
			throw new IllegalArgumentException("The item argument cannot be null!");
		}

		return new BillItem(item.getName(), BigDecimal.valueOf(item.getPrice()).add(BigDecimal.valueOf(salesTax)).doubleValue(), item.getCategory(), salesTax, item.getCount());
	}
	
}
