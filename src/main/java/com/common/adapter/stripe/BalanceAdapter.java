package com.common.adapter.stripe;

import com.stripe.model.Balance;

/*****************************************************
 * 
 * @author dk
 *
 */
public class BalanceAdapter {

	public static BalanceAdapter convert(Balance balance) {
		if(balance == null) {
			return null;
		}
		System.out.println(balance);
		BalanceAdapter result = new BalanceAdapter();
		
		return result;
	}
}
