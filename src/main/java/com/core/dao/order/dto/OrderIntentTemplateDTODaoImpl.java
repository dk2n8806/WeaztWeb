package com.core.dao.order.dto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.dto.order.CustomerOrderIntentTemplateDTO;
import com.common.dto.order.OrderIntentTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.account.Account_;
import com.common.entity.account.Avatar;
import com.common.entity.account.Avatar_;
import com.common.entity.category.Category_;
import com.common.entity.merchant.Merchant;
import com.common.entity.order.OrderIntent;
import com.common.entity.order.OrderIntent_;
import com.common.entity.product.Product;
import com.common.entity.product.Product_;
import com.common.entity.product.embeded.BasicInfo_;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.Subscription_;
import com.common.entity.subscription.embeded.ValuePerShipment_;
import com.common.entity.support.embeded.ImagePath_;
import com.common.type.OrderIntentStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class OrderIntentTemplateDTODaoImpl
extends GenericJpaRepository<OrderIntentTemplateDTO, Long> implements OrderIntentTemplateDTODao {
	
		
	/** :::
	 * <p>Retrieve a list of order_intent templates</p>
	 * 
	 * @query
	 * [
	 * 		Select TEMP
	 * 		From {@link OrderIntentTemplateDTO} TEMP
	 * 			Join TEMP.subscription S
	 * 			Join S.product P
	 * 		Where P.merchant = :merchant
	 * 			And TEMP.status = :status
	 * 			And TEMP.scheduledDate Between :from And :to
	 * 		Order By TEMP.id DESC
	 * ]
	 * 
	 * {@link OrderIntentTemplateDTODao#getTemplates(Merchant, OrderStatus, DateInterval, Pageable)}
	 */
	@Override
	public List<OrderIntentTemplateDTO> getTemplates(Merchant merchant,
			OrderIntentStatus status, DateInterval dateInterval, Pageable pageable) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderIntentTemplateDTO> query =
				builder.createQuery(OrderIntentTemplateDTO.class);
		Root<OrderIntent> root = query.from(OrderIntent.class);
		Join<OrderIntent, Subscription> joinSubscription = root.join(OrderIntent_.subscription);
		Join<Subscription, Product> joinProduct = joinSubscription.join(Subscription_.product);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(merchant != null) {
			predicates.add(builder.equal(joinProduct.get(Product_.merchant), merchant));
		}
		if(status != null) {
			predicates.add(builder.equal(root.get(OrderIntent_.status), status));
		}
		if(dateInterval != null) {
			predicates.add(builder.between(root.get(OrderIntent_.scheduledDate)
													, dateInterval.getFrom(), dateInterval.getTo()));
		}
		
		query.select(builder.construct(OrderIntentTemplateDTO.class
									, root.get(OrderIntent_.id)
									, joinProduct.get(Product_.id)
									, joinProduct.get(Product_.basicInfo).get(BasicInfo_.title)
									, joinProduct.get(Product_.displayImage).get(ImagePath_.path)
									, joinProduct.get(Product_.category).get(Category_.name)
									, joinProduct.get(Product_.status)
									, joinSubscription.get(Subscription_.perShipment).get(ValuePerShipment_.subscriptionCost)
									, joinSubscription.get(Subscription_.status)
								)
							)
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(root.get(OrderIntent_.id)));
		return this.getResultList(query, pageable);
	}
	
	
	
	
	/**
	 * <p>Retrieve a list of customer_order_intent templates</p>
	 * @query
	 * [
	 * 		Select new {@link CustomerOrderIntentTemplateDTO}
	 * 		From {@link OrderIntent} O
	 * 			Join O.subscription S
	 * 			Join S.customer C
	 * 			Join C.profile P
	 * 		Where S.product = :product
	 * 			And O.status = :status
	 * 			And S.status = :status
	 * 			And O.createdOn Between :dateInterval.from And :dateInterval.to
	 * ]
	 * 
	 * {@link OrderIntentTemplateDTODao#getCustomerTemplates(Product, OrderStatus, DateInterval, Pageable)}
	 */
	@Override
	public List<CustomerOrderIntentTemplateDTO> getCustomerTemplates(
			Product product, OrderIntentStatus status, DateInterval dateInterval, Pageable pageable) {
		
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CustomerOrderIntentTemplateDTO> query = builder.createQuery(CustomerOrderIntentTemplateDTO.class);
		Root<OrderIntent> rootOrder = query.from(OrderIntent.class);
		Join<OrderIntent, Subscription> joinSubscription = rootOrder.join(OrderIntent_.subscription);
		Join<Subscription, Account> joinAccount = joinSubscription.join(Subscription_.account);
		Join<Account, Avatar> joinAvatar = joinAccount.join(Account_.avatar);
		
		
		List<Predicate> predicates = new LinkedList<Predicate>();
		if(product != null){
			predicates.add(builder.equal(joinSubscription.get(Subscription_.product), product));
		}
		if(status != null) {
			predicates.add(builder.equal(rootOrder.get(OrderIntent_.status), status));
		}
		if(dateInterval != null) {
			predicates.add(builder.between(rootOrder.get(OrderIntent_.scheduledDate)
															, dateInterval.getFrom(), dateInterval.getTo()));
		}

		query.select(builder.construct(CustomerOrderIntentTemplateDTO.class
									, rootOrder
									, joinSubscription.get(Subscription_.product)
									, joinSubscription.get(Subscription_.id)
									, joinAccount.get(Account_.username)
									, joinAvatar.get(Avatar_.imagePath).get(ImagePath_.path)
									, joinSubscription.get(Subscription_.perShipment).get(ValuePerShipment_.subscriptionCost)
									, joinSubscription.get(Subscription_.status)
									, joinSubscription.get(Subscription_.scheduledOn)
							)
						)
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(rootOrder.get(OrderIntent_.id)));
		
		
		if(pageable != null) {
			return entityManager.createQuery(query)
							.setFirstResult(pageable.getOffset())
							.setMaxResults(pageable.getPageSize())
							.getResultList();
		} 
		return entityManager.createQuery(query).getResultList();
	}

	
	
}
