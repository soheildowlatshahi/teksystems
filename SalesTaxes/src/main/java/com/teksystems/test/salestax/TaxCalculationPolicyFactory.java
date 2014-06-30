package com.teksystems.test.salestax;



/**
 * 
 * @author Soheil Dowlatshahi(soheildowlatshahi@yahoo.com)
 *
 */
public interface TaxCalculationPolicyFactory {
	
	

	/**
	 * 
	 * @param item
	 * @return the applicable policy for given item
	 */
	TaxCalculationPolicy getTaxCalculationPolicy(final Item item);
}
