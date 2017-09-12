package com.core.dao.account.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.common.entity.account.Avatar;
import com.common.entity.account.Avatar_;
import com.common.entity.support.embeded.ImagePath_;
import com.core.dao.account.AvatarDao;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class AvatarDaoImpl 
	extends GenericJpaRepository<Avatar	, Long>
implements AvatarDao
{

	@Override
	public void setActive(Long id, boolean active) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<Avatar> query = builder.createCriteriaUpdate(Avatar.class);
		Root<Avatar> root = query.from(Avatar.class);
		
		query.set(root.get(Avatar_.imagePath).get(ImagePath_.active), active)
				.where(builder.equal(root.get(Avatar_.id), id));
		entityManager.createQuery(query).executeUpdate();
	}

}
