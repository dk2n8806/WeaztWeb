package com.common.util;

import java.util.Random;

public class RandomStringGenerator {

	private static final char[] symbols;
	private final char[] buf;
	
	
	static {
		StringBuilder tmp = new StringBuilder();
	    for (char ch = '0'; ch <= '9'; ++ch)
		tmp.append(ch);
	    for (char ch = 'a'; ch <= 'z'; ++ch)
	    	tmp.append(ch);
	    symbols = tmp.toString().toCharArray();
	}   
	
	
	private final Random random = new Random();
	
	  
	
	  
	public RandomStringGenerator(int length) {
		if (length < 1)
			throw new IllegalArgumentException("length must be > 1: [" + length + "]");
		buf = new char[length];
	}
	
	  
	public String nextString() {
		for (int idx = 0; idx < buf.length; ++idx) 
			buf[idx] = symbols[random.nextInt(symbols.length)];
		return new String(buf);  
	}
	
	
	
	
	public static void main(String args[]) {
		System.out.println(new RandomStringGenerator(10).nextString());
	}


	  
	
}
