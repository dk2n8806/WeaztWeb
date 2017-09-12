package com.web.controller.exception.global;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Resource Not Found")
public class GeneralResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GeneralResourceNotFoundException() {}
	public GeneralResourceNotFoundException(String errorMsg) {
		super(errorMsg);
	}
	 
	public GeneralResourceNotFoundException(Throwable cause) {
		super(cause);
	}
	public GeneralResourceNotFoundException(String errorMsg, Throwable cause) {
		super(errorMsg, cause);
	}
	
	

}
