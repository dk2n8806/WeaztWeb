package com.core.service.tracker;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.tracking.PageTracker;

public interface PageTrackerService {

	PageTracker save(PageTracker tracker);
	
	List<PageTracker> getPage(Pageable pageable);
	
	Long count();
}
