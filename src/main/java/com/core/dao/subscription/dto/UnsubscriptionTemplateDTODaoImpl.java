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

import com.common.dto.subscription.UnsubscriptionTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.category.Category_;
import com.common.entity.product.Product;
import com.common.entity.product.Product_;
import com.common.entity.product.embeded.BasicInfo_;
import com.common.entity.subscription.Subscription;
import com.common.entity.subscription.Subscription_;
import com.common.entity.subscription.Unsubscription;
import com.common.entity.subscription.Unsubscription_;
import com.common.entity.support.embeded.ImagePath_;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class UnsubscriptionTemplateDTODaoImpl 
	extends GenericJpaRepository<UnsubscriptionTemplateDTO, Long> 
implements UnsubscriptionTemplateDTODao{

	@Override
	public List<UnsubscriptionTemplateDTO> getTemplates(Account account, DateInterval dateInterval, Pageable pageable) {

		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UnsubscriptionTemplateDTO> query = builder.createQuery(UnsubscriptionTemplateDTO.class);
		Root<Unsubscription> root = query.from(Unsubscription.class);
		Join<Unsubscription, Subscription> joinSubscription = root.join(Unsubscription_.subscription);
		Join<Subscription, Product> joinProduct = joinSubscription.join(Subscription_.product);
		
		List<Predicate> predicates = new ArrayList<>();
		if(account != null) 
			predicates.add(builder.equal(joinSubscription.get(Subscription_.account), account));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(Unsubscription_.createdOn)
														, dateInterval.getFrom(), dateInterval.getTo()));	
		
		query.select(builder.construct(UnsubscriptionTemplateDTO.class
															, joinProduct.get(Product_.id)
															, joinProduct.get(Product_.basicInfo).get(BasicInfo_.title)
															, joinProduct.get(Product_.displayImage).get(ImagePath_.path)
															, joinProduct.get(Product_.category).get(Category_.name)
															, root))
			.where(predicates.toArray(new Predicate[]{}))
			.orderBy(builder.desc(root));
		
		return getResultList(query, pageable);
	}

}
