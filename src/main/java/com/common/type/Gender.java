package com.common.type;

public enum Gender {

	MALE
	, FEMALE
	;
	
	public static Gender lookup(String gender) {
		try {
			switch (gender.trim().toLowerCase()) {
			case "male": return MALE;
			case "female" : return FEMALE;
			default: return null;
			}
		} catch(NullPointerException e) {
			return null;
		}
	}
}
