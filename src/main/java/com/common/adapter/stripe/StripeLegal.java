package com.common.adapter.stripe;

import java.util.HashMap;
import java.util.Map;

import com.common.entity.support.embeded.BirthDate;


public class StripeLegal {

	private String legalFirstName;
	private String legalLastName;
	private BirthDate birthDate;
	private LegalType legalType;
	
	
	public StripeLegal() {}
	
	
	public StripeLegal(String legalFirstName, String legalLastName,
			BirthDate birthDate, LegalType legalType) {
		super();
		this.legalFirstName = legalFirstName;
		this.legalLastName = legalLastName;
		this.birthDate = birthDate;
		this.legalType = legalType;
	}

	

	public Map<String, Object> getLegal() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dob", getBod());
		params.put("type", legalType.getCode());
		params.put("first_name", legalFirstName);
		params.put("last_name", legalLastName);
		return params;
	}
	
	
	private Map<String, Object> getBod() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("day", birthDate.getDate());
		params.put("month", birthDate.getMonth());
		params.put("year", birthDate.getYear());
		return params;
	}


	
	
	public String getLegalFirstName() {
		return legalFirstName;
	}


	public void setLegalFirstName(String legalFirstName) {
		this.legalFirstName = legalFirstName;
	}


	public String getLegalLastName() {
		return legalLastName;
	}


	public void setLegalLastName(String legalLastName) {
		this.legalLastName = legalLastName;
	}


	public BirthDate getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(BirthDate birthDate) {
		this.birthDate = birthDate;
	}

	public LegalType getLegalType() {
		return legalType;
	}
	public void setLegalType(LegalType legalType) {
		this.legalType = legalType;
	}
	
	
}
