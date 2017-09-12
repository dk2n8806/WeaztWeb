package com.web.response;

import java.io.Serializable;


public class JsonResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean state;
	private Object result;
	
	public JsonResponse() {
		this.state = false;
	}
	
	
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
}
