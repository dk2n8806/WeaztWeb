package com.common.util;

import org.springframework.data.domain.PageRequest;

public class PageSearch extends PageRequest {

	private static final long serialVersionUID = 1L;


	public PageSearch(int page, int size) {
		super(page, size);
	}

	public PageSearch(int page, double size) {
		super(page, (int)size);
	}

}
