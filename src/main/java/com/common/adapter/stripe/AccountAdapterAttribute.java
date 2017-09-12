package com.common.adapter.stripe;

import java.util.HashMap;
import java.util.Map;



/*****************************************************
 * 
 * @author dk
 *
 */
public class AccountAdapterAttribute extends AbstactStripeAdapterAttribute {

	private String displayName;
	private StripeLegal legal;
	private StripeTos tos;
	private CardTokenAdapterAttribute cardToken;
	
	
	
	

	public AccountAdapterAttribute() {}
	public AccountAdapterAttribute(String displayName, StripeTos tos, CardTokenAdapterAttribute cardToken) {
		super();
		this.displayName = displayName;
		this.tos = tos;
		this.cardToken = cardToken;
	}
	
	


	public Map<String, Object> getManageMetadata() {
		Map<String, Object> params = this.getMetadata();
		params.put("managed", true);
		return params;
	}
	

	public Map<String, Object> getStandaloneMetadata() {
		Map<String, Object> params = this.getMetadata();
		params.put("managed", false);
		return params;
	}
	
	
	@Override
	public Map<String, Object> getMetadata() {
		Map<String, Object> params = new HashMap<String, Object>();
		if(displayName != null){
			params.put("business_name", displayName);
		}
		if(cardToken != null) {
			params.put("external_account", cardToken.getTokenId());
		}
		if(legal != null) {
			params.put("legal_entity", legal.getLegal());	
		}
		if(tos != null) {
			params.put("tos_acceptance", tos.getTosMetadata());
		}
		return params;
	}
	
	
	
	public String getDisplayName() {		return displayName;	}
	public void setDisplayName(String displayName) {	this.displayName = displayName;}
	
	
	
	public StripeLegal getLegal() {		return legal;	}
	public void setLegal(StripeLegal legal) {		this.legal = legal;	}

	
	
	public StripeTos getTos() {		return tos;	}
	public void setTos(StripeTos tos) {this.tos = tos;}
	
	
	
}
