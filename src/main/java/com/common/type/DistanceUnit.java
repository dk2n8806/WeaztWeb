package com.common.type;


public enum DistanceUnit {

	CM("cm"),
	MM("mm"),
	M("m"),
	IN("in"),
	FT("ft"),
	YD("yd")
	;
	
	private String code;
	
	private DistanceUnit(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	

	public static DistanceUnit lookup(String code) {
		try {
			switch (code.trim().toLowerCase()) {
			case "cm": return CM;
			case "mm": return MM;
			case "m" : return M;
			case "in": return IN;
			case "ft": return FT;
			case "yd": return YD;
			default: return null;
			}
		} catch(NullPointerException e) {
			return null;
		}
	}
}
