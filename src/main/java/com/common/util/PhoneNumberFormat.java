package com.common.util;

public class PhoneNumberFormat {

	private String number;
	
	public PhoneNumberFormat (String number) {
		String no = number.replaceAll("[^\\d.]", "");
		if(no.length() != 10) {
			throw new IllegalArgumentException("Invalid phone number");
		}
		this.number = no;
	}
	
	public String getPrivateNumber() {
		return this.number;
	}
	
	public String getFirst3Digits() {
		return this.number.substring(0, 3);
	}
	
	
	public static void main(String args[]) {
		PhoneNumberFormat format = new PhoneNumberFormat("123-123-1231");
		System.out.println(format.getPrivateNumber());
		System.out.println(format.getFirst3Digits());
	}
	
}
