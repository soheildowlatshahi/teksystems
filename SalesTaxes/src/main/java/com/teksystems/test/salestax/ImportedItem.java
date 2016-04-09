package com.teksystems.test.salestax;

import com.teksystems.test.salestax.impl.AbstractItem;


/**
 * 
 * @see com.teksystems.test.salestax.impl.AbstractItem class.
 * @author Soheil Dowlatshahi(soheildowlatshahi@yahoo.com)
 *
 */
public class ImportedItem extends AbstractItem {
	
	
	/**
	 * 
	 * @param name
	 * @param price
	 */
	private ImportedItem(final String name, final double price, final ItemCategory category, final int count) {
		super(name, price, category, count);
	}
	
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		final StringBuilder sb = new StringBuilder("ImportedItem(");
		
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
	 * @param count
	 * @return
	 */
	public static ImportedItem newImportedItem(final String name, final double price, final ItemCategory category, final int count) {
		return new ImportedItem(name, price, category, count);
	}
	
	/**
	 * 
	 * @param name
	 * @param price
	 * @return
	 */
	public static ImportedItem newImportedItem(final String name, final double price, final ItemCategory category) {
		return new ImportedItem(name, price, category, 1);
	}

}
