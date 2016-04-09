package com.teksystems.test.salestax.impl;

import java.math.BigDecimal;

import com.teksystems.test.salestax.BillItem;
import com.teksystems.test.salestax.ImportedItem;
import com.teksystems.test.salestax.Item;
import com.teksystems.test.salestax.TaxCalculationPolicy;

/**
 * 
 * @see com.teksystems.test.salestax.TaxCalculationPolicy
 * 
 * @author Soheil Dowlatshahi(soheildowlatshahi@yahoo.com)
 *
 */
public final class ImportedItemTaxCalculationPolicy extends AbstractTaxCalculationPolicy {

	/**
	 * 
	 */
	public static final ImportedItemTaxCalculationPolicy INSTANCE = new ImportedItemTaxCalculationPolicy();
	
	
	/**
	 * 
	 */
	private static final double ADDITIONAL_TAX_PERCENTAGE = 0.05D;
	
	/**
	 * 
	 */
	private static final double SALES_TAX_PERCENTAGE = 0.1D;
	

	/**
	 * 
	 */
	private ImportedItemTaxCalculationPolicy() {
		
	}
	
	/**
	 * 
	 * @see TaxCalculationPolicy#isApplicable(Item)
	 * 
	 */
	@Override
	public boolean isApplicable(final Item item) {
		return item instanceof ImportedItem;
	}

	/**
	 * 
	 * @see TaxCalculationPolicy#calculate(Item) 
	 * 
	 */
	@Override
	public BillItem calculate(final Item item) {
		validateItem(item);
		return createBillItem((ImportedItem) item);
	}
	
	/**
	 * 
	 * @param item
	 */
	private void validateItem(final Item item) {
		if(item == null) {
			throw new IllegalArgumentException("The given item is null!");
		}
		
		if (!(item instanceof ImportedItem)) {
			throw new IllegalArgumentException("The given item is not an ImportedItem!");
		}
	}
	
	/**
	 * 
	 * @param item
	 * @return
	 */
	private static BillItem createBillItem(final ImportedItem item) {
		return BillItem.newInstance(item, calculateSalesTax(item));
	}
	
	/**
	 * 
	 * @param item
	 * @return
	 */
	private static double calculateSalesTax(final ImportedItem item) {
		BigDecimal salesTax = SalesTaxUtils.round(item.getPrice() * ADDITIONAL_TAX_PERCENTAGE);
		
		if (isSalesTaxApplicable(item.getCategory())) {
			salesTax =  salesTax.add(SalesTaxUtils.round(item.getPrice() * SALES_TAX_PERCENTAGE));
		}
		return salesTax.doubleValue();
	}
	
	
}
