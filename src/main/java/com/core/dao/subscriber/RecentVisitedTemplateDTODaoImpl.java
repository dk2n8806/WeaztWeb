package com.core.dao.subscriber;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.dto.product.RecentViewProductTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.category.Category_;
import com.common.entity.product.Product;
import com.common.entity.product.Product_;
import com.common.entity.product.embeded.BasicInfo_;
import com.common.entity.subscriber.RecentVisited;
import com.common.entity.subscriber.RecentVisited_;
import com.common.entity.support.embeded.ImagePath_;
import com.common.type.ProductStatus;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class RecentVisitedTemplateDTODaoImpl 
	extends GenericJpaRepository<RecentViewProductTemplateDTO, Long>
implements RecentVisitedTemplateDTODao
{

	/** 
	 * {@link RecentVisitedTemplateDTODao#getProducts} 
	 * */
	@Override
	public List<RecentViewProductTemplateDTO> getProducts(Account account, ProductStatus status, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecentViewProductTemplateDTO> query = builder.createQuery(RecentViewProductTemplateDTO.class);
		Root<RecentVisited> root = query.from(RecentVisited.class);
		Join<RecentVisited, Product> joinProduct = root.join(RecentVisited_.product);

		List<Predicate> predicates = new ArrayList<>();
		if(account != null) 
			predicates.add(builder.equal(root.get(RecentVisited_.account), account));
		if(status != null) 
			predicates.add(builder.equal(joinProduct.get(Product_.status), status));
			

		
		query.select(builder.construct(RecentViewProductTemplateDTO.class
														,  joinProduct.get(Product_.id)
														, joinProduct.get(Product_.basicInfo).get(BasicInfo_.title)
														, joinProduct.get(Product_.basicInfo).get(BasicInfo_.price)
														, joinProduct.get(Product_.displayImage).get(ImagePath_.path)
														, joinProduct.get(Product_.category).get(Category_.name)
													)
							)
				.where(predicates.toArray(new Predicate[]{}))
				.orderBy(builder.desc(root.get(RecentVisited_.lastVisited)));
		
		return entityManager.createQuery(query)
									.setFirstResult(pageable.getOffset())
									.setMaxResults(pageable.getPageSize())
									.getResultList();
	}

}
