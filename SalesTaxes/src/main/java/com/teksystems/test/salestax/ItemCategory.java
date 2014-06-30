package com.teksystems.test.salestax;

/**
 * 
 * @author Soheil Dowlatshahi(soheildowlatshahi@yahoo.com)
 *
 */
public enum ItemCategory {
	
	MISCELLANEOUS(true),
	BOOKS(false),
	FOOD(false),
	MEDICAL_PRODUCTS(false);
	
	private boolean salesTaxApplicable;
	
	private ItemCategory(final boolean salesTaxApplicable) {
		this.salesTaxApplicable = salesTaxApplicable;
	}
	
	
	public boolean isSalesTaxApplicable() {
		return this.salesTaxApplicable;
	}
	

}
