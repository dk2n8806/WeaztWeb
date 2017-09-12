package com.common.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum USAStates {


	ALABAMA("AL", 851)
	, ALASKA("AK", 169)
	, ARIZONA("AZ", 817)
	, ARKANSAS("AR", 919)
	, CALIFORNIA("CA", 841)
	, COLORADO("CO", 739)
	, CONNECTICUT("CT", 635)
	, DELAWARE("DE", 000)
	, FLORIDA("FL", 662)
	, GEORGIA("GA", 697)
	, HAWAII("HI", 435)
	, IDAHO("ID", 603)
	, ILLINOIS("IL", 816)
	, INDIANA("IN", 700)
	, IOWA("IA",  678)
	, KANSAS("KS", 815)
	, KENTUCKY("KY", 600)
	, LOUISIANA("LA", 889)
	, MAINE("ME", 550)
	, MARYLAND("MD", 600)
	, MASSACHUSETTS("MA", 625)
	, MICHIGAN("MI", 600)
	, MINNESOTA("MN", 719)
	, MISSISSIPPI("MS", 700)
	, MISSOURI("MO", 758)
	, MONTANA("MT", 000)
	, NEBRASKA("NE", 679)
	, NEVADA("NV", 793)
	, NEW_HAMPSHIRE("NH", 000)
	, NEW_JERSEY("NJ", 697)
	, NEW_MEXICO("NM", 726)
	, NEW_YORK("NY", 847)
	, NORTH_COROLINA("NC", 690)
	, NORTH_DAKOTA("ND", 655)
	, OHIO("OH", 711)
	, OKLAHOMA("OK", 872)
	, OREGON("OR", 000)
	, PENNSYLVANIA("PA", 634)
	, RHODE_ISLAND("RI", 700)
	, SOUTH_CAROLINA("SC", 719)
	, SOUTH_DAKOTA("SD", 583)
	, TENNESSEE("TN", 945)
	, TEXAS("TX", 815)
	, UTAH("UT", 668)
	, VERMONT("VT", 614)
	, VIRGINIA("VA", 563)
	, WASHINGTON("WA", 888)
	, WEST_VIRGINIA("WV", 607)
	, WISCONSIN("WI", 543)
	, WYOMING("WY", 549)
	;
	
	private String state;
	private int taxRate;
	
	private USAStates(String state, int taxRate) {
		this.state = state;
		this.taxRate = taxRate;
	}
	
	
	public String getState() {
		return state;
	}
	
	public int getTaxRate() {
		return taxRate;
	}
	
	private static Map<String, USAStates> lookup =
				new HashMap<String, USAStates>();
	
	static {
		for(USAStates e : EnumSet.allOf(USAStates.class)) {
			lookup.put(e.state, e);
		}
	}
	
	public static USAStates lookup(String code) {
		try {
			return lookup.get(code.toUpperCase().trim());
		} catch(NullPointerException e){
			return null;
		}
	}
}
