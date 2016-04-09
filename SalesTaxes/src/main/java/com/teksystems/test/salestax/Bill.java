package com.teksystems.test.salestax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * This concrete class creates immutable Bill objects.
 * 
 * @author Soheil Dowlatshahi(soheildowlatshahi@yahoo.com)
 *
 */
public final class Bill {
	
	/**
	 * 
	 */
	private List<BillItem> items;
	
	/**
	 * 
	 */
	private BigDecimal salesTaxes;
	
	/**
	 * 
	 */
	private BigDecimal total;
	
	/**
	 * 
	 * @param items
	 */
	private Bill(final List<BillItem> items) {
		super();
		this.items = items;
		init(items);
	}
	
	/**
	 * 
	 * Initializes the salesTax and total based on bill items' information
	 * @param items
	 */
	private void init(List<BillItem> items) {
		resetSums();
		calculateSums(items);
	}
	
	/**
	 * 
	 */
	private void resetSums() {
		salesTaxes = BigDecimal.ZERO;
		total = BigDecimal.ZERO;
	}
	
	/**
	 * 
	 * @param items
	 */
	private void calculateSums(final List<BillItem> items) {
		for(BillItem item: items) {
			validateItem(item);
			salesTaxes = salesTaxes.add(BigDecimal.valueOf(item.getSalesTax()));
			total = total.add(BigDecimal.valueOf(item.getPrice()));
		}
	}
	
	
	
	
	
	/**
	 * 
	 * @param item
	 */
	private void validateItem(BillItem item) {
		if (item == null) {
			throw new IllegalArgumentException("The bill items contains at least one null bill item!");
		}
	}
	
	/**
	 * safe copy of bill items to keep immutability principle
	 * @return
	 */
	public List<BillItem> getItems() {
		return new ArrayList<BillItem>(items);
	}
	
	/**
	 * 
	 * @return
	 */
	public double getSalesTaxes() {
		return salesTaxes.doubleValue();
	}
	
	/**
	 * 
	 * @return
	 */
	public double getTotal() {
		return total.doubleValue();
	}

	/**
	 * 
	 * @param billItems
	 * @return
	 */
	public static Bill newInstance(final List<BillItem> billItems) {
		return new Bill(billItems);
	}
}
