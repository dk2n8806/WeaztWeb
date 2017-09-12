package com.common.adapter.stripe.util;

import java.math.BigDecimal;

public class StripeCurrencyAdapter {

	public static final String DEFAULT_CURRENTCY = "usd";
	
	public Integer convertToCent(BigDecimal amount) {
		return new Integer((int) amount.doubleValue() * 100);
	}
	
	public BigDecimal convertToDecimal(Integer amount) {
		return new BigDecimal(Double.valueOf(amount) / 100);
	}
}
