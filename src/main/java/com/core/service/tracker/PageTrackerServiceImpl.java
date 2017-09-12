package com.core.service.tracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.tracking.PageTracker;
import com.core.dao.tracker.PageTrackerDao;

@Service
@Transactional
public class PageTrackerServiceImpl implements PageTrackerService{

	@Autowired private PageTrackerDao trackerDao;
	
	@Override
	public PageTracker save(PageTracker tracker) {
		if(tracker != null) trackerDao.persist(tracker);
		return tracker;
	}

	@Override
	public List<PageTracker> getPage(Pageable pageable) {
		return trackerDao.getPage(pageable);
	}

	@Override
	public Long count() {
		return trackerDao.count();
	}

}
