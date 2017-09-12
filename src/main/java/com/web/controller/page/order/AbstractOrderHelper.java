package com.web.controller.page.order;

import java.util.Date;


import com.common.util.date.DateDistance;
import com.common.util.date.DateInterval;
import com.common.util.date.DateUtil;
import com.web.controller.AbstractMerchantBaseController;

public class AbstractOrderHelper 
extends AbstractMerchantBaseController {
	private final int PAST_DATE = -30;

	protected DateInterval getDateInterval(int date) {
		DateInterval dateInterval = new DateUtil();
		DateDistance dateDistance = new DateUtil();
		if(date < 0) {
			date = 0;
		}
		dateInterval.setInterval(dateDistance.getDateDistance(new Date(), date), PAST_DATE);
		return dateInterval;
	}
	
	protected DateInterval getDateInterval(String _date) {
		DateInterval dateInterval = new DateUtil();
		DateDistance dateDistance = new DateUtil();
		int date = 4;
		/*
		if(_date.equals("today")) {
			date = 0;
		} else if(_date.equals("2days")) {
			date = 1;
		} else if(_date.equals("3days")) {
			date = 2;
		} else if(_date.equals("1week")) {
			date = 6;
		} else if(_date.equals("2weeks")) {
			date = 13;
		} 
		else if(_date.equals("3weeks")) {
			date = 20;
		} 
		else if(_date.equals("4weeks")) {
			date = 27;
		}
		*/
		dateInterval.setInterval(dateDistance.getDateDistance(new Date(), date), PAST_DATE);
		//log.info("Form: " + dateInterval.getFrom());
		//log.info("to: " + dateInterval.getTo());
		return dateInterval;
	}
}
