package com.core.dao.subscription.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.dto.subscription.SubscriptionTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.category.Category_;
import com.common.entity.product.Product;
import com.common.entity.product.Product_;
import com.common.entity.product.embeded.BasicInfo_;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.Subscription_;
import com.common.entity.subscription.embeded.ValuePerShipment_;
import com.common.entity.support.embeded.ImagePath_;
import com.common.type.SubscriptionStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class SubscriptionTemplateDTODaoImpl 
	extends GenericJpaRepository<SubscriptionTemplateDTO, Long>
implements SubscriptionTemplateDTODao
{

	
	/** :::
	 * <p>Get Subscription template</p>
	 * 
	 * @query
	 * [
	 * 		Select New {@link SubscriptionTemplateDTO}
	 * 		From {@link Subscription} S
	 * 		Join S.product P
	 * 		Where S.account = :account
	 * 			And	S.status = :status
	 * 			And S.createdOn Between :from And :to
	 * 		Order By S Desc
	 * ]
	 * 
	 * {@link SubscriptionTemplateDTODao#getTemplates(Account, SubscriptionStatus, DateInterval, Pageable)}
	 */
	@Override
	public List<SubscriptionTemplateDTO> getTemplates(Account account
			, SubscriptionStatus status, DateInterval dateInterval, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SubscriptionTemplateDTO> query =
					builder.createQuery(SubscriptionTemplateDTO.class);
		Root<Subscription> root = query.from(Subscription.class);
		Join<Subscription, Product> joinProduct = root.join(Subscription_.product);

		List<Predicate> predicates = new ArrayList<>();
		if(account != null)
			predicates.add(builder.equal(root.get(Subscription_. account), account));
		if(status != null) 
			predicates.add(builder.equal(root.get(Subscription_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(Subscription_.createdOn)
																, dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(builder.construct(SubscriptionTemplateDTO.class
														,  root.get(Subscription_.id)
														, joinProduct.get(Product_.basicInfo).get(BasicInfo_.title)
														, joinProduct.get(Product_.displayImage).get(ImagePath_.path)
														, joinProduct.get(Product_.category).get(Category_.name)
														, root.get(Subscription_.perShipment).get(ValuePerShipment_.subscriptionValue)
														, root.get(Subscription_.status)
														, root.get(Subscription_.hadShipped)
														, root.get(Subscription_.nos)
														, root.get(Subscription_.scheduledOn)
													)
							)
		
				.where(predicates.toArray(new Predicate[]{}))
				.orderBy(builder.desc(root));
		return getResultList(query, pageable);
	}

	
	
	/** :::
	 * <p>Get Template of a subscription</p>
	 * 
	 * @query
	 * [
	 * 		Select new {@link SubscriptionTemplateDTO}
	 * 		From {@link Subscription} S
	 * 		Where S = :subscription
	 * ]
	 * 
	 * {@link SubscriptionTemplateDTODao#getTemplate(Subscription)}
	 */
	@Override
	public SubscriptionTemplateDTO getTemplate(Subscription subscription) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SubscriptionTemplateDTO> query =
					builder.createQuery(SubscriptionTemplateDTO.class);
		Root<Subscription> root = query.from(Subscription.class);
		Join<Subscription, Product> joinProduct = root.join(Subscription_.product);
		query.select(builder.construct(SubscriptionTemplateDTO.class
												,  root.get(Subscription_.id)
												, joinProduct.get(Product_.basicInfo).get(BasicInfo_.title)
												, joinProduct.get(Product_.displayImage).get(ImagePath_.path)
												, joinProduct.get(Product_.category).get(Category_.name)
												, root.get(Subscription_.perShipment).get(ValuePerShipment_.subscriptionValue)
												, root.get(Subscription_.status)
												, root.get(Subscription_.hadShipped)
												, root.get(Subscription_.nos)
												, root.get(Subscription_.scheduledOn)
												)
							)
				.where(builder.equal(root,  subscription));
		return entityManager.createQuery(query).getSingleResult();
	}

}
