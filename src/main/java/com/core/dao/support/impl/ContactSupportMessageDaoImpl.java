package com.core.dao.support.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.entity.account.Account;
import com.common.entity.support.ContactSupportMessage;
import com.common.entity.support.ContactSupportMessage_;
import com.common.entity.support.SupportCategory;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.support.ContactSupportMessageDao;

@Repository
public class ContactSupportMessageDaoImpl 
extends GenericJpaRepository<ContactSupportMessage, Long>
implements ContactSupportMessageDao{

	
	
/**
 * Count total message;
 * @query
 * [
 * 		Select Count(M) From {@link ContactSupportMessage}
 * 		Where M.account = :account
 * 			And M.category = :category
 * 			And M.isRead = :isRead
 * 			And M.createdOn Between :from And :to
 * ]
 * 
 * {@link ContactSupportMessageDao#countMessage(Account, SupportCategory, Boolean, DateInterval)}
 */
	@Override
	public long countMessage(Account account, SupportCategory category, Boolean isRead, DateInterval dateInterval) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<ContactSupportMessage> root 
					= query.from(ContactSupportMessage.class);
		List<Predicate> predicates = new ArrayList<>();
		if(account !=  null){
			predicates.add(builder.equal(root.get(ContactSupportMessage_.account), account));
		}
		if(category != null) {
			predicates.add(builder.equal(root.get(ContactSupportMessage_.category), category));
		}
		if(isRead != null) {
			predicates.add(builder.equal(root.get(ContactSupportMessage_.read), isRead));
		} 
		if(dateInterval != null) {
			predicates.add(builder.between(root.get(ContactSupportMessage_.createdOn)
										, dateInterval.getFrom(), dateInterval.getTo()));		
		}
		
		query.select(builder.count(root))
			.where(predicates.toArray(new Predicate[]{}));
		
		return entityManager.createQuery(query).getSingleResult();
	}

	
	
	
	
	
	
	
	
	
	

	
/**
 * Retrieve a list of message
 * @query
 * [
 * 		Select M From {@link ContactSupportMessage}
 * 		Where M.account = :account
 * 			And M.category = :category
 * 			And M.isRead = :isRead
 * 			And M.createdOn Between :from And :to
 * 		Order By M DESC
 * ]
 * 
 * {@link ContactSupportMessageDao#getMessages(Account, SupportCategory, Boolean, DateInterval, Pageable)}
 */
	@Override
	public List<ContactSupportMessage> getMessages(Account account
			, SupportCategory category, Boolean isRead,
			DateInterval dateInterval, Pageable pageable) {
		
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ContactSupportMessage> query
					= builder.createQuery(ContactSupportMessage.class);
		Root<ContactSupportMessage> root 
					= query.from(ContactSupportMessage.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if(account !=  null){
			predicates.add(builder.equal(root.get(ContactSupportMessage_.account), account));
		}
		if(category != null) {
			predicates.add(builder.equal(root.get(ContactSupportMessage_.category), category));
		}
		if(isRead != null) {
			predicates.add(builder.equal(root.get(ContactSupportMessage_.read), isRead));
		} 
		if(dateInterval != null) {
			predicates.add(builder.between(root.get(ContactSupportMessage_.createdOn)
										, dateInterval.getFrom(), dateInterval.getTo()));		
		}
		
		query.select(root)
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(root));
		return getResultList(query, pageable);
	}

}
