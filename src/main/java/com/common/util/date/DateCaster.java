package com.common.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateCaster {

	public int monthConverter(String _month) {
		Date date;
		try {
			date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(_month);
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    int month = cal.get(Calendar.MONTH);
		    return month;
	    } catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}
