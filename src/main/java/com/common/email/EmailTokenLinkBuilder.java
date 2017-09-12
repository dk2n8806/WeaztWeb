package com.common.email;


public class EmailTokenLinkBuilder {

	public static String getTokenLink(String url, TokenParam[] params) {
		if(params.length == 0) {
			return null;
		}
		StringBuilder builder = new StringBuilder(EmailInfo.HOST).append(url);

		boolean first = true;
		for(TokenParam param : params) {
			if(first) {
				builder.append("?");
			} else  {
				builder.append("&");
			}
			builder.append(param.getKey()).append("=").append(param.getValue());
			first = false;
		}
	    return builder.toString();
	}
}
