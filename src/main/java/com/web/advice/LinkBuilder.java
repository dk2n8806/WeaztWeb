package com.web.advice;


public class LinkBuilder {

	//private static final String HTTP_HOST = "http://localhost:8085/mingofyweb";
	private String url;
	
	private LinkBuilder() {}
	
	public static LinkBuilder buildUrl(String http_host, String root, LinkParam... params) {
		LinkBuilder link = new LinkBuilder();
		link.setUrl(link.internalBuild(http_host, root, params));
		return link;
	}
	
	public String getUrl() {		return url;}
	private void setUrl(String url) {	this.url = url;}
	
	
	
	private String internalBuild(String http_host, String root, LinkParam...params) {
		StringBuilder builder = new StringBuilder(http_host);
		if(!root.startsWith("/")) {
			builder.append("/");
		}
		builder.append(root);
		for(int i = 0; i < params.length; i++) {
			if(i == 0) {
				builder.append("?");
			} else {
				builder.append("&");
			}
			builder.append(params[i].getKey()).append("=").append(params[i].getValue());
		}
		return builder.toString();
	}
	
	
	
	@Override
	public String toString() {
		return url;
	}
	
	
	public static void main(String args[]) {
		System.out.println(LinkBuilder.buildUrl("host", "/spieltag", new LinkParam("code", "abc"), new LinkParam("id", "1")).toString());
	}
	
	
	
	
	
}
