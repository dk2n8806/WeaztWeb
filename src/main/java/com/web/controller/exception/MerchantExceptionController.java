package com.web.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.common.exception.MerchantException;
import com.common.exception.MerchantNotFoundException;
import com.web.advice.PageAdvice;
import com.web.advice.UriManager;
import com.web.advice.UriPageRequestMapping;

@ControllerAdvice
public class MerchantExceptionController {

	private static final Logger logger = LogManager.getLogger();

	@ExceptionHandler({MerchantException.class})
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public String handleNullPointer(HttpServletRequest request, Exception e) {
		logger.error("[ERROR_URL]: " + request.getRequestURL() 
				+ " - [THROW_ERROR]:" + e.getMessage());
		return PageAdvice.Errors.MERCHANT_EXCEPTION_ERROR;
	}
	

	
	@ExceptionHandler(value=MerchantNotFoundException.class)
	public ModelAndView merchantNotFoundHandler() {
		logger.info("__>.< Catch and Handle MerchantNotFoundException");
		ModelAndView model = new ModelAndView(
									new UriManager()
											.setRequest(
												UriPageRequestMapping.Merchant.NEW_MERCHANT)
											.getRedirectRequest());
		return model;
	}
}
