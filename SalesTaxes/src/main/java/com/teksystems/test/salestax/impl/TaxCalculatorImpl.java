package com.teksystems.test.salestax.impl;

import java.util.ArrayList;
import java.util.List;

import com.teksystems.test.salestax.Bill;
import com.teksystems.test.salestax.BillItem;
import com.teksystems.test.salestax.Item;
import com.teksystems.test.salestax.TaxCalculationPolicy;
import com.teksystems.test.salestax.TaxCalculationPolicyFactory;
import com.teksystems.test.salestax.TaxCalculator;

/**
 * 
 * @see com.teksystems.test.salestax.TaxCalculator interface.
 * @author Soheil Dowlatshahi(soheildowlatshahi@yahoo.com)
 *
 */
public class TaxCalculatorImpl implements TaxCalculator {
	
	/**
	 * 
	 */
	private TaxCalculationPolicyFactory DEFAULT_POLICY_FACTORY = TaxCalculationPolicyFactoryImpl.INSTANCE;
	
	/**
	 * 
	 */
	private final TaxCalculationPolicyFactory policyFactory;
	
	/**
	 * 
	 */
	public TaxCalculatorImpl() {
		super();
		this.policyFactory = DEFAULT_POLICY_FACTORY;
	}
	
	/**
	 * 
	 * @param policyFactory this argument is mandatory
	 */
	public TaxCalculatorImpl(final TaxCalculationPolicyFactory policyFactory) {
		super();
		validatePolicyFactory(policyFactory);
		this.policyFactory = policyFactory;
	}
	
	/**
	 * 
	 * @param policyFactory
	 */
	private static void validatePolicyFactory(final TaxCalculationPolicyFactory policyFactory) {
		if (policyFactory == null) {
			throw new IllegalArgumentException("The policyFactory is null");
		}
	}
	
	/**
	 * 
	 * @see com.teksystems.test.salestax.TaxCalculator#generateBill(Iteratable<Item>)
	 */
	@Override
	public Bill generateBill(final Iterable<Item> items) {
		final List<BillItem> billItems = new ArrayList<BillItem>();
		
		for (Item item: items) {
			billItems.add(generateBillItem(item));
		}
		
		return Bill.newInstance(billItems);
	}
	
	/**
	 * 
	 * @param item
	 * @return
	 */
	private BillItem generateBillItem(final Item item) {
		return getTaxCalculationPolicy(item).calculate(item);
	}
	
	/**
	 * 
	 * @param item
	 * @return
	 */
	private TaxCalculationPolicy getTaxCalculationPolicy(final Item item) {
		return policyFactory.getTaxCalculationPolicy(item);
	}

}
