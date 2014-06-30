package com.teksystems.test.salestax;



/**
 * 
 * @author Soheil Dowlatshahi(soheildowlatshahi@yahoo.com)
 *
 */
public interface TaxCalculator {
	
	/**
	 * 
	 * @param items
	 * @return
	 */
	Bill generateBill(final Iterable<Item> items);

}
