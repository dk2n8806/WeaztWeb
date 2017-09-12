package com.web.controller.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.common.exception.ProductNotFoundException;
import com.web.advice.UriManager;
import com.web.advice.UriPageRequestMapping;


@ControllerAdvice
public class ProductExceptionController {

	private static final Logger log = LogManager.getLogger();
	
	@ExceptionHandler(value=ProductNotFoundException.class)
	public ModelAndView productNotFoundHandler() {
		log.info("__>.< Catch and Handler ProductNotFoundException");
		ModelAndView model = new ModelAndView(
								new UriManager()
										.setRequest((
												UriPageRequestMapping.Errors.PRODUCT_NOT_FOUND))
										.getRedirectRequest());
		return model;
	}
}
