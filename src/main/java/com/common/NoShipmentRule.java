package com.common;


/**
 * Define min and max number of shipment 
 * @author dk2n_
 *
 */
public final class NoShipmentRule {
	
	public static boolean isValidated(int nos) {
		return ((nos < 2) || (nos > 9)) ? false : true;
	}
	
}
