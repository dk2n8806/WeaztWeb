package com.core.dao.account.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.account.Account;
import com.common.entity.account.Account_;
import com.common.entity.account.Profile;
import com.core.dao.account.ProfileDao;
import com.core.dao.generic.GenericJpaRepository;


/*********************************************************************
 * 
 * @author dk
 *
 */
@Repository
public class ProfileDaoImpl extends GenericJpaRepository<Profile, Long> implements ProfileDao 
{

	
	/** :::
	 * 
	 */
	@Override
	public Profile getByAccount(Account customer) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Profile> query = builder.createQuery(Profile.class);
		Root<Account> root = query.from(Account.class);
		query.select(root.get(Account_.profile))
			.where(builder.equal(root, customer));
		return entityManager.createQuery(query).getSingleResult();
	}

	
	
	/** :::
	 * 
	 */
	@Override
	public List<Profile> getProfiles(Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Profile> query = builder.createQuery(Profile.class);
		Root<Profile> root = query.from(Profile.class);
		query.select(root).orderBy(builder.desc(root));
		return getResultList(query, pageable);
	}


}
