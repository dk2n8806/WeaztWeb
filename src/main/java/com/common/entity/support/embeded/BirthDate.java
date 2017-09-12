package com.common.entity.support.embeded;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class BirthDate implements Serializable
{

	private static final long serialVersionUID = 1L;
	private int date;
	private int month;
	private int year;
	
	
	public static BirthDate create(int date, int month, int year) {
		try {
			if(month < 0 || month > 12) {
				throw new IllegalArgumentException("Invalid month");
			}
			// TODO need better checking according to month
			if(date < 0 || date > 12) {
				throw new IllegalArgumentException("Invalid date");
			}
			// TODO need better checking for year
			if(year > 2000 || year < 1900) {
				throw new IllegalArgumentException("Invalid year");
			}
			BirthDate birthDate = new BirthDate();
			birthDate.date = date;
			birthDate.month = month;
			birthDate.year = year;
			return birthDate;
		} catch(IllegalArgumentException e) {
			return null;
		}
	}

	
	

	@Column(name="BD_DATE")
	public int getDate() {	return date;}
	public void setDate(int date) {	this.date = date;}

	@Column(name="BD_MONTH")
	public int getMonth() {	return month;}
	public void setMonth(int month) {	this.month = month;}

	@Column(name="BD_YEAR")
	public int getYear() {	return year;}
	public void setYear(int year) {this.year = year;}
	
	
}
