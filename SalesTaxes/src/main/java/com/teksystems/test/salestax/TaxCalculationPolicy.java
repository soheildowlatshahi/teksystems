package com.teksystems.test.salestax;

/**
 * 
 * @author Soheil Dowlatshahi(soheildowlatshahi@yahoo.com)
 *
 */
public interface TaxCalculationPolicy {
	
	/**
	 * 
	 * @param item
	 * @return true if the policy can be applied on given item type
	 */
	boolean isApplicable(final Item item);
	
	/**
	 * 
	 * @param item a valid item 
	 * @return calculated bill item
	 */
	BillItem calculate(final Item item);

}
