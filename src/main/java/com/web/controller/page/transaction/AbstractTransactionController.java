package com.web.controller.page.transaction;

import java.util.Calendar;

import com.common.util.date.DateInterval;
import com.common.util.date.DateUtil;
import com.web.controller.AbstractMerchantBaseController;

public class AbstractTransactionController extends AbstractMerchantBaseController {

	protected DateInterval getInterval(Integer month, Integer year) {
		DateInterval dateInterval = new DateUtil();
		Calendar calendar = Calendar.getInstance();
		
		if(month == null || year == null) {
			if(month == null) {
				month = calendar.get(Calendar.MONTH) + 1;
			}
			if(year == null) {
				year = calendar.get(Calendar.YEAR);
			}
		}
		if(month <= 0 || month > 12) {
			month = calendar.get(Calendar.MONTH) + 1;
		} 
		if(year < (calendar.get(Calendar.YEAR) - 10)
				|| year > calendar.get(Calendar.YEAR)) {
			year = calendar.get(Calendar.YEAR);
		} 
		
		dateInterval.setInterval(month, year);
		return dateInterval;
	}
}
