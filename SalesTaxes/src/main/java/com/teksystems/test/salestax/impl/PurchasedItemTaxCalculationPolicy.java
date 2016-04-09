package com.teksystems.test.salestax.impl;

import com.teksystems.test.salestax.BillItem;
import com.teksystems.test.salestax.Item;
import com.teksystems.test.salestax.PurchasedItem;
import com.teksystems.test.salestax.TaxCalculationPolicy;

/**
 * 
 * @see com.teksystems.test.salestax.TaxCalculationPolicy interface
 * @author Soheil Dowlatshahi
 *
 */
public final class PurchasedItemTaxCalculationPolicy extends AbstractTaxCalculationPolicy {
	
	/**
	 * 
	 */
	public static final PurchasedItemTaxCalculationPolicy INSTANCE = new PurchasedItemTaxCalculationPolicy();
	
	/**
	 * 
	 */
	private static final double TAX_PERCENTAGE = 0.1D;
	
	/**
	 * Singletone class
	 */
	private PurchasedItemTaxCalculationPolicy() {
		
	}
	
	
	/**
	 *  
	 * @see TaxCalculationPolicy#isApplicable(Item) 
	 */
	@Override
	public boolean isApplicable(final Item item) {
		return item instanceof PurchasedItem;
	}
	
	
	/**
	 * 
	 * @see TaxCalculationPolicy#calculate(Item)
	 */
	@Override
	public BillItem calculate(final Item item) {
		validateItem(item);
		return createBillItem((PurchasedItem) item);
	}
	
	
	/**
	 * 
	 * @param item
	 */
	private static void validateItem(final Item item) {
		if(item == null) {
			throw new IllegalArgumentException("The given item is null!");
		}
		
		if (!(item instanceof PurchasedItem)) {
			throw new IllegalArgumentException("The given item is not a PurchasedItem!");
		}
	}
	
	/**
	 * 
	 * @param item
	 * @return
	 */
	private static BillItem createBillItem(final PurchasedItem item) {
		return BillItem.newInstance(item, calculateSalesTax(item));
	}
	
	/**
	 * 
	 * @param purchasedItem
	 * @return
	 */
	private static double calculateSalesTax(final PurchasedItem purchasedItem) {
		return isSalesTaxApplicable(purchasedItem.getCategory()) 
				? SalesTaxUtils.round(purchasedItem.getPrice() * TAX_PERCENTAGE).doubleValue() 
				: 0;
	}

}
