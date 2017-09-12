package com.common.exception;

public class AccountNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private String errorMsg;
	
	public AccountNotFoundException() {
	}
	
	public AccountNotFoundException(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
	public String getErrorMsg() {
		return errorMsg;
	}

}
