package com.common.entity.product.embeded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * <h1></h1>
 * <p>An embedable class for product</p>
 * 
 * @author dk2n_
 *
 */
@Embeddable
public class BasicInfo {

	private static final Logger logger = LogManager.getLogger();
	private int price;
	private String title;
	private String description;
	
	
	public static BasicInfo create(int price, String title, String description) 
	{
		try {
			if(title.length() == 0) {
				throw new IllegalArgumentException("Title length must be within [0-255]");
			}
			if(price < 500) {
				throw new IllegalArgumentException("Price must be > $5.00");
			}
			BasicInfo basicInfo = new BasicInfo();
			basicInfo.price = price; 
			basicInfo.title = title;
			basicInfo.description = description;
			return basicInfo;
		} catch(IllegalArgumentException e) {
			//e.printStackTrace();
			logger.error(e.getMessage());
			return null;
		}
	}
	
	
	

	

	@Override
	public String toString() {
		return getClass().getName() + " {\n\tprice: " + price + "\n\ttitle: " + title + "\n\tdescription: "
				+ description + "\n}";
	}






	@Column(name="TITLE", nullable=false)
	public String getTitle() {		return title; }
	public void setTitle(String title) { this.title = title; }
	
	
	@Lob
	@Column(name="DESCRIPTION", nullable=false)
	public String getDescription() {	return description;}
	public void setDescription(String description) {		this.description = description; }
	
	
	@Column(name="PRICE", nullable=false)
	public int getPrice() {		return price;	}
	public void setPrice(int price) {	this.price = price;}
	
	
	
	
	
	
}
