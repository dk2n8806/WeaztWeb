package com.common.exception;

public class WeaztException extends Exception {

	private static final long serialVersionUID = 1L;

    public WeaztException(String message) {
        super(message, null);
    }

    public WeaztException(String message, Throwable e) {
        super(message, e);
    }

}
