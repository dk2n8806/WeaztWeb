package com.common.tool;


public class SearchTool {

	private String searchText;
	
	public SearchTool(String searchTest) {
		this.searchText = searchTest;
	}
	
	
	public String[] getSearchFormat() {
		String[] s = searchText.split("\\s+");
		for(int i = 0; i < s.length; i++) {
			s[i] = getSearchFormat(s[i]);
		}
		return s;
	}
	

	private String getSearchFormat(String ref) {
		return new StringBuilder().append("%")
							.append(ref.toLowerCase())
							.append("%")
							.toString();
	}
	
	public static void main(String args[]) {
		String[] s = new SearchTool("a b c").getSearchFormat(); 
		System.out.println("size: " + s.length);
		for(String z : s) {
			System.out.println(z);
		}	
	}
}
