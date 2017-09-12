package com.common.util.date;

import java.util.Calendar;
import java.util.Date;


public class DateUtil
implements DateInterval, DateReset, DateDistance {

	private Date from;
	private Date to;
	
	
	public Date setDateScaleDown(int day, int month, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		return cal.getTime();
	}
	
	
	
	
	/** {@link DateInterval#getFrom()} */
	@Override 	public Date getFrom() { return from;	}
	/** {@link DateInterval#getTo()} */
	@Override  	public Date getTo() {		return to;}
	
	
/*********************************************************************
 * Configure date_interval
 * 
 * @diagram
 * [
 * 		if(daysDistance < 0)
 * 			from_date ----> pivot_date (to_date)
 * 		else 
 * 			pivot_date (from_date) ----> to_date
 * ]
 * 
 * {@link DateInterval#setInterval(Date, int)}
 */
	@Override
	public void setInterval(Date date, int daysDistance) {
		if(daysDistance < 0) {
			to = this.lastestTimeStamp(date);
			from = this.resetTimeStamp(this.getDateDistance(to, daysDistance));
		} else {
			from = this.resetTimeStamp(date);
			to = this.lastestTimeStamp(this.getDateDistance(from, daysDistance));
		}
	}


/*******************************************
 * Configure date_interval which take pivot_date as center point
 * 
 * @diagram
 * [
 * 		from_date ---> pivot_date ----> to_date
 * ]
 * 
 * {@link DateInterval#setInterval(Date, int, int)}
 */
	@Override
	public void setInterval(Date pivotDate, int f, int t) {
		if(f > t) {
			throw new IllegalArgumentException("to-date must be greater than from-date");
		}
		if(f > 0) {
			throw new IllegalArgumentException("from-date must be date in the pass");
		}
		if(t < 0) {
			throw new IllegalArgumentException("to-date must be date in the future");
		}
		from = this.resetTimeStamp(this.getDateDistance(pivotDate, f));
		to = this.lastestTimeStamp(this.getDateDistance(pivotDate, t));
	}

	
/*******************************************************************
 * Configure date_interval
 * 
 * Example
 * [
 * 		if(date1 after date2)
 * 			date1 ----> date2
 * 		else
 * 			date2 ----> date1
 * ]
 * 
 * {@link DateInterval#setInterval(Date, Date)}
 */
	@Override
	public void setInterval(Date date1, Date date2) {
		from = null;
		to = null;
		if(date1.after(date2)) {
			to = this.lastestTimeStamp(date1);
			from = this.resetTimeStamp(date2);
		} else {
			from = this.lastestTimeStamp(date1);
			to = this.resetTimeStamp(date2);
					
		}	
	}
	
/*************************************************************
 * 
 * Example:
 * 	[Input]		date = Wed Jun 01 07:58:42 PDT 2016
 * 	[Output]	form = Wed Jun 01 0:0:0 PDT 2016
 * 				to   = Wed Jun 01 23:59:59 PDT 2016
 */
	@Override
	public void setInterval(Date date) {
		from = this.getFirstDateOfMonth(resetTimeStamp(date));
		to = this.getLastDateOfMonth(lastestTimeStamp(date));
		
	}
	


	
/*************************************************************
 * 
 * Example:
 * 	[Input]		month = 6, year = 2016
 * 	[Output]	form = Jun 01 0:0:0 PDT 2016
 * 				to   = Jun 30 23:59:59 PDT 2016
 */
	@Override
	public void setInterval(int month, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 0);
		setInterval(calendar.getTime());
	}



	
/****************************************************
 * Reset a particular to the beginning point of the date
 * 
 * {@link DateReset#resetTimeStamp(Date)}
 */
	@Override
	public Date resetTimeStamp(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	
/********************************************
 * Configure a date to ending point of the date
 * 
 * {@link DateReset#lastestTimeStamp(Date)}
 */
	@Override
	public Date lastestTimeStamp(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();		
	}
	
	
	public Date getLastDateOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
	public Date getFirstDateOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	
	
	/** Retrieve a date distance from another date. 
	 * <ul>
	 * <li>Negative days: Date in the past</li>
	 * <li>Positive days: Date in the future</li>
	 * </ul>
	 * @param date
	 * @param days
	 * @return
	 */
	@Override
	public Date getDateDistance(Date date, int days) {		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
	
	
	public Date getDateFromWeek(int weekNumber, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.WEEK_OF_YEAR, weekNumber);
		cal.set(Calendar.YEAR, year);
		return cal.getTime();
	}
	




	@Override
	public void setSunToSat(Date date) {
		from = getSpecificDateOfTheWeek(date, Calendar.SUNDAY);
		to = getSpecificDateOfTheWeek(date, Calendar.SATURDAY);
	}


	private Date getSpecificDateOfTheWeek(Date date, int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int today = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.DAY_OF_WEEK, -today + i);
		
		return calendar.getTime();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		//Date now = new Date();
		//Date endOfMonth = new DateUtil().getLastDateOfMonth(now);
		//Date nextDate = new DateUtil().getDateDistance(now, -1);
		/*System.out.println("TestDownloadFile Date Util");
		System.out.println("Now is: " + now);
		System.out.println("End of the month: " + endOfMonth);
		System.out.println("Tommorow: " + nextDate);*/
		
		DateInterval dateInterval = new DateUtil();
		dateInterval.setInterval(3, 2016);
		System.out.println("Date1: " + dateInterval.getFrom());
		System.out.println("Date2: " + dateInterval.getTo());
		
	}



}
