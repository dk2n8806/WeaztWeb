package com.web.advice;

public class LinkParam {

	private String key;
	private String value;
	
	public LinkParam(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public LinkParam(String key, Long value) {
		this.key = key;
		this.value = value.toString();
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
