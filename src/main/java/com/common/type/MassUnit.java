package com.common.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum MassUnit {
	GRAM("g"),
	KG("kg"),
	OZ("oz"),
	LB("lb")
	;
	
	private String code;
	
	private MassUnit(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	
	private static Map<String, MassUnit> lookup =
			new HashMap<String, MassUnit>();
	
	static {
		for(MassUnit code : EnumSet.allOf(MassUnit.class)) 
			lookup.put(code.getCode(), code);
	}
	
	
	public static MassUnit lookup(String code) {
		try {
			switch (code.trim().toLowerCase()) {
			case "g": return GRAM;
			case "kg": return KG;
			case "oz": return OZ;
			case "lb": return LB;
			default: return null;
			}
		} catch(NullPointerException e) {
			return null;
		}
	}
}
