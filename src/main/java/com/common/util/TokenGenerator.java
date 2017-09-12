package com.common.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public class TokenGenerator {

	private static SecureRandom random = new SecureRandom();
	
	public static String generateToken(String prefix) {
		//String token = UUID.randomUUID().toString();
		String token = new BigInteger(130, random).toString(32);
		return prefix + "_" + token;
	}
	
	
	
	public static void main(String args[]) {
		System.out.println(generateToken("token"));
	}
}
