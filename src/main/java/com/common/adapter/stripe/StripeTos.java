package com.common.adapter.stripe;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StripeTos {

	public String tosDate;
	public String tosIp;
	
	
	public static void main(String args[]) {
		System.out.println((long) System.currentTimeMillis() / 1000L);
		System.out.println(new Date((long) System.currentTimeMillis()));
		System.out.println(new Date().getTime() / 1000L);
		
	}
	
	public StripeTos() {}
	public StripeTos(String tosIp) {
		this.tosIp = tosIp;
		this.tosDate = convert(new Date());
	}
	public StripeTos(String tosIp,String tosDate) {
		super();
		this.tosDate = tosDate;
		this.tosIp = tosIp;
	}
	public StripeTos(String tosIp, Date tosDate) {
		super();
		this.tosDate = String.valueOf(tosDate.getTime() / 1000L);
		this.tosIp = tosIp;
	}


	public String convert(Date date) {	
		return String.valueOf(date.getTime() / 1000L);
	}


	
	public Map<String, Object> getTosMetadata() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date", tosDate);
		params.put("ip", tosIp);
		return params;
	}
	
	
	
	
	
	public String getTosDate() {
		return tosDate;
	}
	public void setTosDate(String tosDate) {
		this.tosDate = tosDate;
	}
	public String getTosIp() {
		return tosIp;
	}
	public void setTosIp(String tosIp) {
		this.tosIp = tosIp;
	}
	
	
}
