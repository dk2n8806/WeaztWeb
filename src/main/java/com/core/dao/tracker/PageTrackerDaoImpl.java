package com.core.dao.tracker;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.tracking.PageTracker;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class PageTrackerDaoImpl 
	extends GenericJpaRepository<PageTracker, Long> 
implements PageTrackerDao
{

	@Override
	public List<PageTracker> getPage(Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PageTracker> query = builder.createQuery(PageTracker.class);
		Root<PageTracker> root = query.from(PageTracker.class);
		
		query.select(root).orderBy(builder.desc(root));
		
		return getResultList(query, pageable);
	}

	@Override
	public Long count() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<PageTracker> root = query.from(PageTracker.class);
		query.select(builder.count(root));
		return entityManager.createQuery(query).getSingleResult();
	}

}
