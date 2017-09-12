package com.core.dao.order.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.dto.order.OrderBundleWrapperDTO;
import com.common.entity.category.Category_;
import com.common.entity.merchant.Merchant;
import com.common.entity.order.OrderBundle;
import com.common.entity.order.OrderBundle_;
import com.common.entity.product.Product;
import com.common.entity.product.Product_;
import com.common.entity.product.embeded.BasicInfo_;
import com.common.entity.support.embeded.ImagePath_;
import com.common.type.OrderStatus;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class OrderBundleWrapperDTODaoImpl 
	extends GenericJpaRepository<OrderBundleWrapperDTO, Long>
implements OrderBundleWrapperDTODao
{

	@Override
	public List<OrderBundleWrapperDTO> getByMerchant(
			Merchant merchant, OrderStatus status, DateInterval dateInterval, Pageable pageable) {

		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderBundleWrapperDTO> query = 
								builder.createQuery(OrderBundleWrapperDTO.class);
		Root<OrderBundle> root = query.from(OrderBundle.class);
		Join<OrderBundle, Product> joinProduct = root.join(OrderBundle_.product);
		
		List<Predicate> predicates = new ArrayList<>();
		if(merchant != null)
			predicates.add(builder.equal(joinProduct.get(Product_.merchant), merchant));
		if(status != null)
			predicates.add(builder.equal(root.get(OrderBundle_.status), status));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(OrderBundle_.createdOn)
															, dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(builder.construct(OrderBundleWrapperDTO.class
													, root
													, joinProduct.get(Product_.basicInfo).get(BasicInfo_.title)
													, joinProduct.get(Product_.displayImage).get(ImagePath_.path)
													, joinProduct.get(Product_.category).get(Category_.name)
												)	
							)
				.where(predicates.toArray(new Predicate[]{}))
				.orderBy(builder.desc(root));
		
		return getResultList(query, pageable);
	}

	
	
	/** :::
	 * <p>Retrieve a order bundle wrapper by a merchant</p>
	 * 
	 * {@link OrderBundleWrapperDTODao#getByMerchant(Long, Merchant)}
	 */
	@Override
	public OrderBundleWrapperDTO getByMerchant(Long orderBundleId, Merchant merchant)
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderBundleWrapperDTO> query = 
								builder.createQuery(OrderBundleWrapperDTO.class);
		Root<OrderBundle> root = query.from(OrderBundle.class);
		Join<OrderBundle, Product> joinProduct = root.join(OrderBundle_.product);
		query.select(builder.construct(OrderBundleWrapperDTO.class
													, root
													, joinProduct.get(Product_.basicInfo).get(BasicInfo_.title)
													, joinProduct.get(Product_.displayImage).get(ImagePath_.path)
													, joinProduct.get(Product_.category).get(Category_.name)
												)	
							)
				.where(builder.equal(root.get(OrderBundle_.id), orderBundleId)
				, builder.equal(joinProduct.get(Product_.merchant), merchant));
		return entityManager.createQuery(query).getSingleResult();
	}

}
