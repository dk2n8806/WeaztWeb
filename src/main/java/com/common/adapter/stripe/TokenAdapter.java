package com.common.adapter.stripe;

import com.stripe.model.Token;


/** 
 * <h1>Token Adapter</h1>
 * 
 * 
 * @author dk2n_
 *
 */
public class TokenAdapter {
	private String tokenId;
	
	
	public String getTokenId() {		return tokenId;	}
	public void setTokenId(String tokenId) {	this.tokenId = tokenId;}
	

	public static TokenAdapter create(Token token) {
		if(token == null) {
			return null;
		}
		TokenAdapter result = new TokenAdapter();
		result.setTokenId(token.getId());
		return result;
	}
}
