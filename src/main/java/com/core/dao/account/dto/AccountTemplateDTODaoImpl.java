package com.core.dao.account.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.dto.AccountTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.account.Account_;
import com.common.type.AccountStatus;
import com.common.type.Role;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;


/**
 * 
 * @author dk2n_
 *
 */
@Repository
public class AccountTemplateDTODaoImpl 
	extends GenericJpaRepository<AccountTemplateDTO, Long>
implements AccountTemplateDTODao
{

	
	
	/**
	 * {@link AccountTemplateDTODao#getTemplates(Role, AccountStatus, Pageable)}
	 */
	@Override
	public List<AccountTemplateDTO> getTemplates(Role role
							, AccountStatus status, DateInterval dateInterval, Pageable pageable) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AccountTemplateDTO> query = builder.createQuery(AccountTemplateDTO.class);
		Root<Account> root = query.from(Account.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(role != null)
			predicates.add(builder.equal(root.get(Account_.role), role));
		if(status != null) 
			predicates.add(builder.equal(root.get(Account_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(Account_.createdOn)
														, dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(builder.construct(AccountTemplateDTO.class
													, root.get(Account_.id)
													, root.get(Account_.username)
													, root.get(Account_.email)
													, root.get(Account_.avatar)
													, root.get(Account_.role)
													, root.get(Account_.status)
													, root.get(Account_.createdOn)
													)
				).where(predicates.toArray(new Predicate[]{}))
				.orderBy(builder.desc(root));
		
		return getResultList(query, pageable);
	}

	
	
	/**
	 * {@link AccountTemplateDTODao#getTemplate(Account)}
	 */
	@Override
	public AccountTemplateDTO getTemplate(Account account) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AccountTemplateDTO> query = builder.createQuery(AccountTemplateDTO.class);
		Root<Account> root = query.from(Account.class);
		
		query.select(builder.construct(AccountTemplateDTO.class
													, root.get(Account_.id)
													, root.get(Account_.username)
													, root.get(Account_.email)
													, root.get(Account_.avatar)
													, root.get(Account_.role)
													, root.get(Account_.status)
													, root.get(Account_.createdOn)
													)
				).where(builder.equal(root, account));
		return entityManager.createQuery(query).getSingleResult();
	}

	
}
