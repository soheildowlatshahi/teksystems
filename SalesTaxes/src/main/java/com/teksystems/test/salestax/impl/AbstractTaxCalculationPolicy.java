package com.teksystems.test.salestax.impl;

import com.teksystems.test.salestax.ItemCategory;
import com.teksystems.test.salestax.TaxCalculationPolicy;


/**
 * 
 * @see com.teksystems.test.salestax.TaxCalculationPolicy interface
 * @author Soheil Dowlatshahi(soheildowlatshahi@yahoo.com)
 * 
 *
 */
public abstract class AbstractTaxCalculationPolicy  implements TaxCalculationPolicy {
	
	/**
	 * 
	 */
	protected AbstractTaxCalculationPolicy() {
		
	}
	
	/**
	 * 
	 * @param itemCategory
	 * @return
	 */
	protected static boolean isSalesTaxApplicable(ItemCategory itemCategory) {
		return itemCategory == null || itemCategory.isSalesTaxApplicable();
	}

}
