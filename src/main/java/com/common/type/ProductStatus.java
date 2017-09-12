package com.common.type;

/**
 * 
 * @author dk2n_
 *
 */
public enum ProductStatus {
	
	PUBLIC
	, PRIVATE
	, DELETED
	;
	
	public static ProductStatus lookup(String status) {
		switch (status.trim().toLowerCase()) {
		case "public"	: return PUBLIC;
		case "private"	: return PRIVATE;
		case "deleted"	: return DELETED;
		default: return null;
		}
	}
}
