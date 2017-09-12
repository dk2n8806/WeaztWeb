package com.common.util.date;

import java.util.Date;

public interface DateInterval {

	/** {@link DateUtil#getFrom()} */
	Date getFrom();
	
	
	/** {@link DateUtil#getTo()} */
	Date getTo();
	
	/** {@link DateUtil#setInterval(Date, int)} */
	void setInterval(Date date, int daysDistance);
	
	/** {@link DateUtil#setInterval(Date, int, int)} */
	void setInterval(Date pivotDate, int from, int to);
	
	/** {@link DateUtil#setInterval(Date, Date)} */
	void setInterval(Date date1, Date date2);
	
	/** {@link DateUtil#setInterval(Date)} */
	void setInterval(Date date);
	
	/** {@link DateUtil#setInterval(int, int)} */
	void setInterval(int month, int year);
	
	
	/** Set date.from to Sunday and date.to to Saturday of the  input date.
	 * {@link DateUtil#setSunToSat(Date)} */
	void setSunToSat(Date date);
}
