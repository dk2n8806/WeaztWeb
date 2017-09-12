package com.core.dao.promo.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.promo.NewLetter;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.promo.NewLetterDao;

@Repository
public class NewLetterDaoImpl 
	extends GenericJpaRepository<NewLetter, Long>
	implements NewLetterDao
{

/**
 * Retrieve a list of NewLetter entities
 * 
 * @query
 * [
 * 		Select L
 * 		From {@link NewLetter} L
 * 		Order By L Desc
 * ]
 * 
 * @return a list of NewLetter entities
 */
	@Override
	public List<NewLetter> getList(Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<NewLetter> query = builder.createQuery(NewLetter.class);
		Root<NewLetter> root = query.from(NewLetter.class);
		
		query.select(root).orderBy(builder.desc(root));
		return entityManager.createQuery(query)
							.setFirstResult(pageable.getOffset())
							.setMaxResults(pageable.getPageSize())
							.getResultList();
	}

}
