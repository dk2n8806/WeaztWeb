package com.web.controller.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.web.advice.PageAdvice;


@ControllerAdvice
public class GeneralExceptionController {

	private static final Logger logger = LogManager.getLogger();
	
	@ExceptionHandler({NoHandlerFoundException.class})
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public String handleNullPointer() {
		logger.error("-----------Error------------");
		return PageAdvice.Errors.PAGE_404;
	}
	
	
}
