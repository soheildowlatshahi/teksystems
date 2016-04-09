package com.teksystems.test.salestax.impl;

import com.teksystems.test.salestax.Item;
import com.teksystems.test.salestax.TaxCalculationPolicy;
import com.teksystems.test.salestax.TaxCalculationPolicyFactory;

/**
 * 
 * @see com.teksystems.test.salestax.TaxCalculationPolicyFactory interface.
 * @author Soheil Dowlatshahi(soheildowlatshahi@yahoo.com)
 *
 */
public class TaxCalculationPolicyFactoryImpl implements TaxCalculationPolicyFactory {
	
	/**
	 * Singletone instance
	 */
	public static final TaxCalculationPolicyFactoryImpl INSTANCE = new TaxCalculationPolicyFactoryImpl();
	
	/**
	 * 
	 */
	private static final TaxCalculationPolicy[] POLICIES = new TaxCalculationPolicy[] {
		ImportedItemTaxCalculationPolicy.INSTANCE,
		PurchasedItemTaxCalculationPolicy.INSTANCE
	};
	
	/**
	 * Singletone Class
	 */
	private TaxCalculationPolicyFactoryImpl() {
		
	}

	/**
	 * 
	 * @param item
	 * @return
	 */
	public TaxCalculationPolicy getTaxCalculationPolicy(final Item item) {
		for (TaxCalculationPolicy policy : POLICIES) {
			if (policy.isApplicable(item)) {
				return policy;
			}
		}
		
		throw new IllegalArgumentException("No TaxCalculationPolicy defined for given item!");
	}
}

