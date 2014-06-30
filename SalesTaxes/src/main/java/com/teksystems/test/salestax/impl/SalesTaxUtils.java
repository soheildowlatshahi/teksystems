package com.teksystems.test.salestax.impl;

import java.math.BigDecimal;

/**
 * Utility class for tax calculations
 * 
 * @author Soheil Dowlatshahi(soheildowlatshahi@yahoo.com)
 *
 */
public final class SalesTaxUtils {
	
	/**
	 * 
	 */
	private SalesTaxUtils() {
		throw new UnsupportedOperationException("Utility calss cannot be intialized!");
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public static BigDecimal round(double amount) {
		amount = amount * 100.0;
		long roundAmount = Math.round(amount);
		long fraction = roundAmount % 10;
		roundAmount = roundAmount - fraction;
		
		if (fraction != 0) {
			if (fraction < 5) {
				fraction = 5;
			} else if (fraction > 5) {
				fraction = 10;
			}
		}
		
		
		return  BigDecimal.valueOf(0.01).multiply(BigDecimal.valueOf(roundAmount + fraction));
	}
}
