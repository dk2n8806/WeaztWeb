package com.web.advice;

import java.util.HashMap;
import java.util.Map;

public class UriManager {

	private Map<String, String> paramMap;
	private StringBuilder requestUri;
	
	

	private Map<String, String> getParamMap() {
		return paramMap;
	}
	
	private StringBuilder getRequestUri() {
		return requestUri;
	}
	
	public UriManager append(String param, Object value) {
		Map<String, String> paramMap = this.getParamMap();
		if(paramMap == null) {
			paramMap = new HashMap<String, String>();
		}
		if(value != null && !String.valueOf(value).equals("")){
			paramMap.put(param, String.valueOf(value));
		}
		return this;
	}
	
	
	
	public UriManager setRequest(String uri) {
		paramMap = new HashMap<String, String>();
		requestUri = new StringBuilder().append(uri);
		return this;
	}
	
	
	
	public String getRequest() {
		boolean first = true;
		StringBuilder builder = new StringBuilder();
		builder.append(this.getRequestUri());
		for(Map.Entry<String, String> entry : this.getParamMap().entrySet()) {
			if(first) {
				builder.append("?");
				first = false;
			} else {
				builder.append("&&");
			}
			builder.append(entry.getKey())
					.append("=")
					.append(entry.getValue());
		}
		
		return builder.toString();
	}
	
	

	public String getRedirectRequest() {
		return new StringBuilder().append("redirect:")
								.append(this.getRequest())
								.toString();
	}
	
	
	
	public static void main(String args[]) {

		System.out.println(new UriManager().setRequest("/new")
											//.append("a", 1)
											.append("b", "asda")
											.getRedirectRequest());
		/*System.out.println("alo " + UriPageRequestMapping.User.Checkout.CHECKOUT_CONFIRM);
		*/
		System.out.println(new UriManager()
									.setRequest(UriPageRequestMapping.User.Checkout.CHECKOUT_CONFIRM)
									.getRedirectRequest());
		
	}
	
	
	
}
