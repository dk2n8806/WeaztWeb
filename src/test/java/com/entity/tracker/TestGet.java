package com.entity.tracker;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.common.entity.tracking.PageTracker;
import com.core.service.tracker.PageTrackerService;

public class TestGet extends BaseTest 
{
	@Autowired private PageTrackerService service;
	
	@Test
	public void test() {
		List<PageTracker> pages = service.getPage(null);
		
		for(PageTracker page : pages) {
			System.out.println(page.getAccount() + " - " + page.getIp());
		}
	}
	
	
}
