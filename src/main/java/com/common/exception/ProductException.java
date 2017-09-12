package com.common.exception;

public class ProductException extends WeaztException {


	public ProductException(String message) {
		super(message);
	}
	
	public ProductException(String message, Throwable e) {
		super(message, e);
	}

	private static final long serialVersionUID = 1L;
}
