package com.common.entity.support;

import java.util.ArrayList;
import java.util.List;

public enum SupportCategory {


	GENERAL(1, "General information about Weazt")
	, MERCHANT(2, "Become Weazt's seller")
	, SUBSCRIBING(3, "Question aboubt subscribing an item")
	, LISTING(4, "List an item on Weazt")
	, SELLING(5, "Question abount selling an item")
	, ORDER(6, "Process orders and shipments")
	, OTHER(100, "other")
	;
	
	private int code;
	private String category;
	
	private SupportCategory(int code, String category) {
		this.code = code;
		this.category = category;
	}
	
	public int getCode() {
		return code;
	}
	public String getCategory() {
		return category;
	}
	
	
	public static List<SupportCategory> getCategories(){
		List<SupportCategory> categories = new ArrayList<>();
		categories.add(GENERAL);
		categories.add(MERCHANT);
		categories.add(LISTING);
		categories.add(ORDER);
		categories.add(OTHER);
		return categories;
	}
	
	public static SupportCategory lookup(int code) {
		switch (code) {
		case 1: return GENERAL;
		case 2: return MERCHANT;
		case 3: return SUBSCRIBING;
		case 4: return LISTING;
		case 5: return SELLING;
		case 6: return ORDER;
		case 100: return OTHER;
		default: return null;
		}
	}
	
}
