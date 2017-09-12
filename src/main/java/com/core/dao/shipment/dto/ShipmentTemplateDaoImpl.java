package com.core.dao.shipment.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.dto.shipment.RecurrentSubscriptionShipmentTemplateDTO;
import com.common.dto.shipment.LastSubscriptionShipmentTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.category.Category_;
import com.common.entity.order.OrderIntent;
import com.common.entity.order.OrderIntent_;
import com.common.entity.order.OrderTransaction;
import com.common.entity.order.OrderTransaction_;
import com.common.entity.order.Shipment;
import com.common.entity.product.Product;
import com.common.entity.product.Product_;
import com.common.entity.product.embeded.BasicInfo_;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.Subscription_;
import com.common.entity.support.embeded.ImagePath_;
import com.common.type.OrderIntentStatus;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class ShipmentTemplateDaoImpl 
extends GenericJpaRepository<LastSubscriptionShipmentTemplateDTO, Long> implements ShipmentTemplateDao{
	
	/** :::
	 * <p>Retrieve shipment transactions of subscription</p>
	 * 
	 * @query
	 * [
	 * 		Select New {@link LastSubscriptionShipmentTemplateDTO}
	 * 		From {@link OrderTransaction} OT
	 * 			Join OT.shipment S1
	 * 			Join OT.subscription S2
	 * 			Join S2.product
	 * 		Where S2.account = :account
	 * 			And S2 = :subscription
	 * 		Order By S1 DESC 
	 * ]
	 * 
	 * {@link ShipmentTemplateDao#getHistoryShipments(Account, Subscription, Pageable)}
	 * ::: */
	@Override
	public List<LastSubscriptionShipmentTemplateDTO> getHistoryShipments(
			Account account, Subscription subscription, Pageable pageable) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<LastSubscriptionShipmentTemplateDTO> query = builder.createQuery(LastSubscriptionShipmentTemplateDTO.class);
		Root<OrderTransaction> root = query.from(OrderTransaction.class);
		Join<OrderTransaction, Shipment> joinShipment  = root.join(OrderTransaction_.shipment);
		Join<OrderTransaction, Subscription> joinSubscription = root.join(OrderTransaction_.subscription);
		Join<Subscription, Product> joinProduct = joinSubscription.join(Subscription_.product);
		
		List<Predicate> predicates = new ArrayList<>();
		if(account != null) {
			predicates.add(builder.equal(joinSubscription.get(Subscription_.account), account));
		}
		
		if(subscription != null) {
			predicates.add(builder.equal(joinSubscription, subscription));
		}
		
		query.select(builder.construct(
								LastSubscriptionShipmentTemplateDTO.class
								, joinProduct.get(Product_.id)
								, joinProduct.get(Product_.basicInfo).get(BasicInfo_.title)
								, joinProduct.get(Product_.displayImage).get(ImagePath_.path)
								, joinProduct.get(Product_.category).get(Category_.name)
								, joinShipment))
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(joinShipment));
		
		return entityManager.createQuery(query)
							.setFirstResult(pageable.getOffset())
							.setMaxResults(pageable.getPageSize())
							.getResultList();
	}

		
	/** :::
	 * <p>Retrieve a list of future shipment of the subscription</p>
	 * @query
	 * [
	 * 		Select NEW {@link RecurrentSubscriptionShipmentTemplateDTO}
	 * 		From {@link OrderIntent} OI
	 * 			Join OI.subscription S
	 * 			Join S.product P
	 * 		Where S.account = :account
	 * 			And S = :subscription
	 * ]
	 * 
	 * {@link ShipmentTemplateDao#getNextShipments(Account, Subscription, Pageable)}
	 */
	@Override
	public List<RecurrentSubscriptionShipmentTemplateDTO> getNextShipments(Account account,
			Subscription subscription, Pageable pageable) 
	{
	
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecurrentSubscriptionShipmentTemplateDTO> query 
								= builder.createQuery(RecurrentSubscriptionShipmentTemplateDTO.class);
		Root<OrderIntent> root = query.from(OrderIntent.class);
		Join<OrderIntent, Subscription> joinSubscription 
								= root.join(OrderIntent_.subscription);
		Join<Subscription, Product> joinProduct 
								= joinSubscription.join(Subscription_.product);
		
		List<Predicate> predicates = new ArrayList<>();
		if(account != null) {
			predicates.add(builder.equal(joinSubscription.get(Subscription_.account), account));
		}
		if(subscription != null) {
			predicates.add(builder.equal(joinSubscription, subscription));
		}
		predicates.add(builder.equal(root.get(OrderIntent_.status), OrderIntentStatus.REQUESTING));
		
		query.select(builder.construct(
								RecurrentSubscriptionShipmentTemplateDTO.class
								, joinProduct.get(Product_.id)
								, joinProduct.get(Product_.basicInfo).get(BasicInfo_.title)
								, joinProduct.get(Product_.displayImage).get(ImagePath_.path)
								, joinProduct.get(Product_.category).get(Category_.name)
								, root.get(OrderIntent_.scheduledDate)))
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.asc(root.get(OrderIntent_.scheduledDate)));
		
		return entityManager.createQuery(query)
							.setFirstResult(pageable.getOffset())
							.setMaxResults(pageable.getPageSize())
							.getResultList();
	}

	
	

}
