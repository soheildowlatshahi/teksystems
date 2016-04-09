package com.teksystems.test.salestax.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.teksystems.test.salestax.Bill;
import com.teksystems.test.salestax.Item;
import com.teksystems.test.salestax.ItemCategory;
import com.teksystems.test.salestax.TaxCalculator;

import static com.teksystems.test.salestax.PurchasedItem.newPurchasedItem;
import static com.teksystems.test.salestax.ImportedItem.newImportedItem;

import static com.teksystems.test.salestax.ItemCategory.BOOKS;
import static com.teksystems.test.salestax.ItemCategory.FOOD;
import static com.teksystems.test.salestax.ItemCategory.MISCELLANEOUS;
import static com.teksystems.test.salestax.ItemCategory.MEDICAL_PRODUCTS;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;


/**
 * This test case is written to test TaxCalculatorImpl behavior.
 * 
 * @see com.teksystems.test.salestax.impl.TaxCalculatorImpl.TaxCalculatorImpl class.
 * @author Soheil Dowlatshahi
 *
 */
public class TaxCalculatorTest {
	
	/**
	 * 
	 */
	private TaxCalculator taxCalculator;
	
	/**
	 * 
	 */
	private List<Item> items;
	
	/**
	 * 
	 */
	private Bill bill;
	
	/**
	 * 
	 * @throws Exception
	 */
	@Before
	public void startUp() throws Exception {
		this.items = new ArrayList<Item>();
		this.taxCalculator = new TaxCalculatorImpl();
		this.bill = null;
	}
	
	/**
	 * 
	 * @see com.teksystems.test.salestax.TaxCalculator#generateBill(Iterable<Item>)
	 */
	@Test
	public void testGenerateBill1() {
		addPurchasedItem("book", 12.49, BOOKS);
		addPurchasedItem("music CD", 14.99, MISCELLANEOUS);
		addPurchasedItem("chocolate bar", 0.85, FOOD);

		runCalculator();
		
		assertBill(1.50, 29.83);
	}
	
	/**
	 * @see com.teksystems.test.salestax.TaxCalculator#generateBill(Iterable<Item>)
	 */
	@Test
	public void testGenerateBill2() {
		addImportedItem("box of imported chocolates", 10.00, FOOD);
		addImportedItem("imported bottle of perfume", 47.50, MISCELLANEOUS);
	
		runCalculator();
		
		assertBill(7.65, 65.15);
	}
	
	/**
	 * @see com.teksystems.test.salestax.TaxCalculator#generateBill(Iterable<Item>)
	 */
	@Test
	public void testGenerateBill3() {
		addImportedItem("imported bottle of perfume", 27.99, MISCELLANEOUS);
		addPurchasedItem("bottle of perfume", 18.99, MISCELLANEOUS);
		addPurchasedItem("packet of headache pill", 9.75, MEDICAL_PRODUCTS);
		addImportedItem("box of imported chocolates", 11.25, FOOD);	
		
		runCalculator();
		
		assertBill(6.70, 74.68);

	}
	
	/**
	 * 
	 * @param name
	 * @param price
	 * @param category
	 */
	private void addPurchasedItem(final String name, final double price, final ItemCategory category) {
		items.add(newPurchasedItem(name, price, category));
	}
	
	/**
	 * 
	 * @param name
	 * @param price
	 */
	private void addImportedItem(final String name, final double price, final ItemCategory category) {
		items.add(newImportedItem(name, price, category));
	}
	
	
	/**
	 * 
	 */
	private void runCalculator() {
		bill = taxCalculator.generateBill(items);
	}
	
	/**
	 * 
	 * @param salesTaxes
	 * @param total
	 */
	private void assertBill(final double salesTaxes, final double total) {
		assertNotNull(bill);
		assertEquals(salesTaxes, bill.getSalesTaxes(), 0);
		assertEquals(total, bill.getTotal(), 0);
	}
	
	
	/**
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception  {
		if (this.items != null) {
			this.items.clear();
			this.items = null;			
		}
		this.bill = null;
		taxCalculator = null;
	}
	


}
