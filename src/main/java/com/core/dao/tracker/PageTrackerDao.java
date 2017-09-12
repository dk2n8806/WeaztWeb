package com.core.dao.tracker;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.common.entity.tracking.PageTracker;
import com.core.dao.generic.GenericRepository;

public interface PageTrackerDao extends GenericRepository<PageTracker, Long>{

	List<PageTracker> getPage(Pageable pageable);
	
	Long count();
}
